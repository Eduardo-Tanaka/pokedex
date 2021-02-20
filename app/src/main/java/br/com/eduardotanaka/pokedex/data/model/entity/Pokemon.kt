package br.com.eduardotanaka.pokedex.data.model.entity

import androidx.room.PrimaryKey

data class Pokemon(
    //@PrimaryKey
    var id: Int,
    var name: String,
    var url: String
)