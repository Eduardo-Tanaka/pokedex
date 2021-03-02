package br.com.eduardotanaka.pokedex.network

import br.com.eduardotanaka.pokedex.data.model.api.PokemonGenerationResponse
import br.com.eduardotanaka.pokedex.data.model.api.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// GET https://pokeapi.co/api/v2/generation/{id or name}/
interface PokedexService {

    @GET("/api/v2/generation/{generation}")
    suspend fun getPokemonByGeneration(
        @Path("generation") generation: Int,
    ): Response<PokemonGenerationResponse>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<PokemonResponse>
}