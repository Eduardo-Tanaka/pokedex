package br.com.eduardotanaka.pokedex.data.room.dao.base

import androidx.room.Dao
import androidx.room.Query
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonGeneration

@Dao
abstract class PokemonGenerationDao : BaseDao<PokemonGeneration>() {

    @Query("SELECT * FROM PokemonGeneration")
    abstract fun getAll(): List<PokemonGeneration>
}