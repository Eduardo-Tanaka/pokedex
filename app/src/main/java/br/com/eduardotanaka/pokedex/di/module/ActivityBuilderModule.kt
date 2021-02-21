package br.com.eduardotanaka.pokedex.di.module

import br.com.eduardotanaka.pokedex.ui.MainActivity
import br.com.eduardotanaka.pokedex.ui.pokemonlist.PokemonListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Colocar as activities aqui e caso necessário importar os modules
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesPokemonListActivity(): PokemonListActivity
}