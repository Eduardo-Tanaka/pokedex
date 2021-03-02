package br.com.eduardotanaka.pokedex.data.model.entity

import android.os.Parcelable
import br.com.eduardotanaka.pokedex.data.model.api.PokemonGenerationResponse
import br.com.eduardotanaka.pokedex.data.model.entity.base.ReflectsApiResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSpecies(
    var pokemonGeneration: List<PokemonGeneration> = ArrayList()
) : ReflectsApiResponse<PokemonSpecies, PokemonGenerationResponse>, Parcelable {
    override fun reflectFrom(apiResponse: PokemonGenerationResponse): PokemonSpecies {
        apiResponse.pokemonSpecies.let { result ->
            pokemonGeneration = result.map {
                PokemonGeneration(
                    it.url.split("https://pokeapi.co/api/v2/pokemon-species/")[1].filter { it.isDigit() }
                        .toInt(),
                    apiResponse.id,
                    it.name,
                    it.url
                )
            }
        }

        return this
    }

}