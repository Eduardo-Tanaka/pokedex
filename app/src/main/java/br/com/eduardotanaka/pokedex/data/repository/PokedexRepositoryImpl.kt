package br.com.eduardotanaka.pokedex.data.repository

import br.com.eduardotanaka.pokedex.data.model.entity.base.PokemonSpecie
import br.com.eduardotanaka.pokedex.data.repository.base.BaseRepository
import br.com.eduardotanaka.pokedex.network.PokedexService
import retrofit2.Response
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService
) : BaseRepository(), PokedexRepository {

    override suspend fun getGeneration(generation: Int): PokemonSpecie {
        val response: Response<out Any?>
        response = pokedexService.getPokemonByGeneration(1)
        return PokemonSpecie(ArrayList())
    }

}