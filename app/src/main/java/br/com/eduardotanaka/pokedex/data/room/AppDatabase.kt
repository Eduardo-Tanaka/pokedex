package br.com.eduardotanaka.pokedex.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonGeneration
import br.com.eduardotanaka.pokedex.data.room.converter.Converters
import br.com.eduardotanaka.pokedex.data.room.dao.base.PokemonGenerationDao

@Database(
    version = 2,
    entities = [
        PokemonGeneration::class,
    ],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonGenerationDao(): PokemonGenerationDao

}