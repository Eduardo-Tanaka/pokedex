package br.com.eduardotanaka.pokedex.network

import br.com.eduardotanaka.pokedex.data.model.api.PokemonGenerationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// GET https://pokeapi.co/api/v2/generation/{id or name}/
interface PokedexService {

    @GET("/generation/{generation}")
    suspend fun getCotacao(
        @Path("generation") generation: Int,
    ): Response<PokemonGenerationResponse>
}