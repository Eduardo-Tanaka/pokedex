package br.com.eduardotanaka.pokedex.data.repository

import android.content.SharedPreferences
import br.com.eduardotanaka.pokedex.constants.CacheKey
import br.com.eduardotanaka.pokedex.data.model.api.PokemonGenerationResponse
import br.com.eduardotanaka.pokedex.data.model.entity.base.PokemonSpecies
import br.com.eduardotanaka.pokedex.data.repository.base.BaseRepository
import br.com.eduardotanaka.pokedex.data.repository.base.Resource
import br.com.eduardotanaka.pokedex.data.repository.helpers.DataFetchHelper
import br.com.eduardotanaka.pokedex.data.room.AppDatabase
import br.com.eduardotanaka.pokedex.network.PokedexService
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService,
    private val appDatabase: AppDatabase,
    private val sharedPreferences: SharedPreferences
) : BaseRepository(), PokedexRepository {

    override suspend fun getGeneration(generation: Int): Resource<PokemonSpecies> {
        val dataFetchHelper = object : DataFetchHelper.LocalFirstUntilStale<PokemonSpecies>(
            "PokedexRepositoryImpl",
            sharedPreferences,
            CacheKey.POKEDEX_GENERATION.toString(),
            "Dados das gerações de pokemon",
            TimeUnit.HOURS.toSeconds(24 * 30)
        ) {
            override suspend fun getDataFromLocal(): PokemonSpecies? {
                return PokemonSpecies(appDatabase.pokemonGenerationDao().getAll())
            }

            override suspend fun getDataFromNetwork(): Response<out Any?> {
                return pokedexService.getPokemonByGeneration(1)
            }

            override suspend fun convertApiResponseToData(response: Response<out Any?>): PokemonSpecies {
                return PokemonSpecies()
                    .reflectFrom(response.body() as PokemonGenerationResponse)
            }

            override suspend fun storeFreshDataToLocal(data: PokemonSpecies): Boolean {
                data.let {
                    appDatabase.pokemonGenerationDao().insert(data.pokemonGeneration)
                    return true
                }
            }

            override suspend fun operateOnDataPostFetch(data: PokemonSpecies) {
            }
        }

        return dataFetchHelper.fetchDataIOAsync().await()
    }

}

