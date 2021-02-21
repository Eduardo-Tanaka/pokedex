package br.com.eduardotanaka.pokedex.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.data.room.dao.base.BaseDao

@Dao
abstract class PokemonDao : BaseDao<Pokemon>() {

    @Query("SELECT * FROM Pokemon WHERE id = :id")
    abstract fun getById(id: Int): Pokemon

    @Query("SELECT * FROM Pokemon")
    abstract fun getAll(): List<Pokemon>
}