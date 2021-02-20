package br.com.eduardotanaka.pokedex.ui

import androidx.lifecycle.LiveData
import br.com.eduardotanaka.pokedex.data.model.entity.base.PokemonSpecies
import br.com.eduardotanaka.pokedex.ui.base.StatefulResource

interface MainActivityViewModel {

    val pokemonSpecies: LiveData<StatefulResource<PokemonSpecies>>

    fun getPokemonByGeneration(generation: Int)
}