package br.com.eduardotanaka.pokedex.data.model.api

import br.com.eduardotanaka.pokedex.data.model.api.base.ApiResponseObject
import com.google.gson.annotations.SerializedName

data class StatObjectResponse(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: StatResponse
) : ApiResponseObject