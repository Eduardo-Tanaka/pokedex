package br.com.eduardotanaka.pokedex.data.model.api

import br.com.eduardotanaka.pokedex.data.model.api.base.ApiResponseObject
import com.google.gson.annotations.SerializedName

data class TypeObjectResponse(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeResponse
) : ApiResponseObject