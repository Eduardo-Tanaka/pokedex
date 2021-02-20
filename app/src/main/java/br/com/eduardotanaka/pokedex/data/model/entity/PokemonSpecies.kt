package br.com.eduardotanaka.pokedex.data.model.entity.base

import br.com.eduardotanaka.pokedex.data.model.api.PokemonGenerationResponse
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonGeneration
import java.io.Serializable

data class PokemonSpecies(
    var pokemonGeneration: List<PokemonGeneration> = ArrayList()
) : ReflectsApiResponse<PokemonSpecies, PokemonGenerationResponse>, Serializable {
    override fun reflectFrom(apiResponse: PokemonGenerationResponse): PokemonSpecies {
        apiResponse.pokemonSpecies.let { result ->
            pokemonGeneration = result.map {
                PokemonGeneration(
                    it.url.split("https://pokeapi.co/api/v2/pokemon-species/")[1].filter { it.isDigit() }
                        .toInt(),
                    it.name,
                    it.url
                )
            }
        }

        return this
    }

}