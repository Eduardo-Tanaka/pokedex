package br.com.eduardotanaka.pokedex.data.model.api

import br.com.eduardotanaka.pokedex.data.model.api.base.ApiResponseObject
import com.google.gson.annotations.SerializedName

data class PokemonGenerationResponse(
    @SerializedName("pokemon_species")
    val pokemonSpecies: List<PokemonSpeciesResponse>
) : ApiResponseObject