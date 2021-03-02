package br.com.eduardotanaka.pokedex.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class PokemonGeneration(
    @PrimaryKey
    var id: Int,
    var generation: Int,
    var name: String,
    var url: String
) : Parcelable