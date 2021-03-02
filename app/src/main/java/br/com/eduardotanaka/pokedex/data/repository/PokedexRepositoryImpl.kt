package br.com.eduardotanaka.pokedex.data.repository

import android.content.SharedPreferences
import br.com.eduardotanaka.pokedex.constants.CacheKey
import br.com.eduardotanaka.pokedex.data.model.api.PokemonGenerationResponse
import br.com.eduardotanaka.pokedex.data.model.api.PokemonResponse
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonSpecies
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
            CacheKey.POKEDEX_GENERATION.toString() + "_" + generation,
            "Dados das gerações de pokemon",
            TimeUnit.HOURS.toSeconds(24 * 30)
        ) {
            override suspend fun getDataFromLocal(): PokemonSpecies? {
                return PokemonSpecies(
                    appDatabase.pokemonGenerationDao().getByGeneration(generation)
                )
            }

            override suspend fun getDataFromNetwork(): Response<out Any?> {
                return pokedexService.getPokemonByGeneration(generation)
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
                /*data.pokemonGeneration.let { result ->
                    result.map {
                        getPokemon(it.id, generation)
                    }
                }*/
            }
        }

        return dataFetchHelper.fetchDataIOAsync().await()
    }

    override suspend fun getAllPokemon(generation: Int): Resource<List<Pokemon>> {
        val dataFetchHelper = object : DataFetchHelper.LocalOnly<List<Pokemon>>(
            "PokedexRepositoryImpl",
        ) {
            override suspend fun getDataFromLocal(): List<Pokemon> {
                var list = appDatabase.pokemonDao().getAllByGeneration(generation)
                if (list.isEmpty()) {
                    val data = PokemonSpecies(
                        appDatabase.pokemonGenerationDao().getByGeneration(generation)
                    )
                    data.pokemonGeneration.let { result ->
                        result.map {
                            getPokemon(it.id, generation)
                        }
                    }
                    list = appDatabase.pokemonDao().getAllByGeneration(generation)
                }

                return list
            }
        }

        return dataFetchHelper.fetchDataIOAsync().await()
    }

    override suspend fun getPokemon(id: Int, generation: Int): Resource<Pokemon> {
        val dataFetchHelper = object : DataFetchHelper.LocalFirstUntilStale<Pokemon>(
            "PokedexRepositoryImpl",
            sharedPreferences,
            CacheKey.POKEDEX_POKEMON.toString() + "_" + id,
            "Cache de pokemon pelo id",
            TimeUnit.HOURS.toSeconds(24 * 30)
        ) {
            override suspend fun getDataFromLocal(): Pokemon? {
                return appDatabase.pokemonDao().getById(id)
            }

            override suspend fun getDataFromNetwork(): Response<out Any?> {
                return pokedexService.getPokemon(id)
            }

            override suspend fun convertApiResponseToData(response: Response<out Any?>): Pokemon {
                return Pokemon().reflectFrom(response.body() as PokemonResponse)
            }

            override suspend fun storeFreshDataToLocal(data: Pokemon): Boolean {
                data.let {
                    data.generation = generation
                    appDatabase.pokemonDao().insert(data)
                    return true
                }
            }
        }

        return dataFetchHelper.fetchDataIOAsync().await()
    }
}

