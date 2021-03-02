package br.com.eduardotanaka.pokedex.data.repository

import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonSpecies
import br.com.eduardotanaka.pokedex.data.repository.base.Resource

interface PokedexRepository {

    suspend fun getGeneration(generation: Int): Resource<PokemonSpecies>

    suspend fun getAllPokemon(generation: Int): Resource<List<Pokemon>>
    suspend fun getPokemon(id: Int, generation: Int): Resource<Pokemon>
}