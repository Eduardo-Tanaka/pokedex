package br.com.eduardotanaka.pokedex.di

import br.com.eduardotanaka.pokedex.data.repository.PokedexRepository
import br.com.eduardotanaka.pokedex.data.repository.PokedexRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Classes repository colocar aqui
 */
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokedexkRepository(pokedexRepository: PokedexRepositoryImpl): PokedexRepository

}