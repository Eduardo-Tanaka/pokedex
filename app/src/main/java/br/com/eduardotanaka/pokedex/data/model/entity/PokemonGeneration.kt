package br.com.eduardotanaka.pokedex.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonGeneration(
    @PrimaryKey
    var id: Int,
    var name: String,
    var url: String
)