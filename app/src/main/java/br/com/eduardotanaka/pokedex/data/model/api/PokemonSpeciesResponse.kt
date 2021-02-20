package br.com.eduardotanaka.pokedex.data.model.api

import br.com.eduardotanaka.pokedex.data.model.api.base.ApiResponseObject

data class PokemonSpeciesResponse(
    val name: String,
    val url: String
) : ApiResponseObject