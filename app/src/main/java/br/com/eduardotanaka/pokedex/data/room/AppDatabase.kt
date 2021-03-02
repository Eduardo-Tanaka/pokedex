package br.com.eduardotanaka.pokedex.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonGeneration
import br.com.eduardotanaka.pokedex.data.room.converter.Converters
import br.com.eduardotanaka.pokedex.data.room.dao.PokemonDao
import br.com.eduardotanaka.pokedex.data.room.dao.PokemonGenerationDao

@Database(
    version = 5,
    entities = [
        Pokemon::class,
        PokemonGeneration::class,
    ],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonGenerationDao(): PokemonGenerationDao
    abstract fun pokemonDao(): PokemonDao
}