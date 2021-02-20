package br.com.eduardotanaka.pokedex.data.model.entity.base

import androidx.room.Entity
import br.com.eduardotanaka.pokedex.data.model.api.PokemonGenerationResponse
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import java.io.Serializable

//@Entity
data class PokemonSpecie(
    var pokemon: List<Pokemon>
) : ReflectsApiResponse<PokemonSpecie, PokemonGenerationResponse>, Serializable {
    override fun reflectFrom(apiResponse: PokemonGenerationResponse): PokemonSpecie {
        apiResponse.pokemonSpecies.let { result ->
            pokemon = result.map {
                Pokemon(
                    it.url.split("https://pokeapi.co/api/v2/pokemon-species/")[1].toInt(),
                    it.name,
                    it.url
                )
            }
        }

        return this
    }

}