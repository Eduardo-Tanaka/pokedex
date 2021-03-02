package br.com.eduardotanaka.pokedex.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonGeneration
import br.com.eduardotanaka.pokedex.data.room.dao.base.BaseDao

@Dao
abstract class PokemonGenerationDao : BaseDao<PokemonGeneration>() {

    @Query("SELECT * FROM PokemonGeneration")
    abstract fun getAll(): List<PokemonGeneration>

    @Query("SELECT * FROM PokemonGeneration WHERE generation = :generation")
    abstract fun getByGeneration(generation: Int): List<PokemonGeneration>
}