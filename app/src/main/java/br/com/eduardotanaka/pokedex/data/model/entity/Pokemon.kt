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
    var name: String = "",
    var generation: Int = 0,
    var height: Int = 0,
    var weight: Int = 0,
    var types: String = "",
    var hp: Int = 0,
    var attack: Int = 0,
    var defense: Int = 0,
    var specialAttack: Int = 0,
    var specialDefense: Int = 0,
    var speed: Int = 0,
) : ReflectsApiResponse<Pokemon, PokemonResponse>, Parcelable {
    override fun reflectFrom(apiResponse: PokemonResponse): Pokemon {
        apiResponse.let { result ->
            this.id = result.id
            this.urlImage = result.sprites.other.officialArtwork.frontDefault
            this.name = result.name
            this.generation = result.generation
            this.height = result.height
            this.weight = result.weight
            var typesList = ArrayList<String>()
            result.types.let { res ->
                res.map {
                    typesList.add(it.type.name)
                }
            }
            this.types = typesList.joinToString(",")
            this.hp = result.stats[0].baseStat
            this.attack = result.stats[1].baseStat
            this.defense = result.stats[2].baseStat
            this.specialAttack = result.stats[3].baseStat
            this.specialDefense = result.stats[4].baseStat
            this.speed = result.stats[5].baseStat
        }

        return this
    }
}