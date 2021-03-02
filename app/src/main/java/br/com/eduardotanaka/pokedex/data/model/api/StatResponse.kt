package br.com.eduardotanaka.pokedex.data.model.api

import br.com.eduardotanaka.pokedex.data.model.api.base.ApiResponseObject
import com.google.gson.annotations.SerializedName

data class StatResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : ApiResponseObject