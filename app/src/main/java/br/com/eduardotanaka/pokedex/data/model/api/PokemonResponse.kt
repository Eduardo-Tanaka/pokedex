package br.com.eduardotanaka.pokedex.data.model.api

import br.com.eduardotanaka.pokedex.data.model.api.base.ApiResponseObject
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: SpritesResponse,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("types")
    val types: List<TypeObjectResponse>,
    @SerializedName("stats")
    val stats: List<StatObjectResponse>,
    var generation: Int
) : ApiResponseObject