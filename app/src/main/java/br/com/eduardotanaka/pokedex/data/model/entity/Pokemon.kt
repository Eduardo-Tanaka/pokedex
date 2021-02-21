package br.com.eduardotanaka.pokedex.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.eduardotanaka.pokedex.data.model.api.PokemonResponse
import br.com.eduardotanaka.pokedex.data.model.entity.base.ReflectsApiResponse
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Pokemon(
    @PrimaryKey
    var id: Int = 0,
    var urlImage: String = "",
    var name: String = ""
) : ReflectsApiResponse<Pokemon, PokemonResponse>, Parcelable {
    override fun reflectFrom(apiResponse: PokemonResponse): Pokemon {
        apiResponse.let { result ->
            this.id = result.id
            this.urlImage = result.sprites.other.officialArtwork.frontDefault
            this.name = result.name
        }

        return this
    }
}