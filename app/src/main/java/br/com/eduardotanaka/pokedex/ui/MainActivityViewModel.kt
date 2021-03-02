package br.com.eduardotanaka.pokedex.ui

import androidx.lifecycle.LiveData
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonSpecies
import br.com.eduardotanaka.pokedex.ui.base.StatefulResource

interface MainActivityViewModel {

    val pokemonSpecies: LiveData<StatefulResource<PokemonSpecies>>
    fun getPokemonByGeneration(generation: Int)

    val pokemon: LiveData<StatefulResource<Pokemon>>
    fun getPokemon(id: Int, generation: Int)

    val pokemonsList: LiveData<StatefulResource<List<Pokemon>>>
    fun getPokemons(generation: Int)
}