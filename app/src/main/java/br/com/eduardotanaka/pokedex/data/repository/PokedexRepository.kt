package br.com.eduardotanaka.pokedex.data.repository

import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.data.model.entity.base.PokemonSpecies
import br.com.eduardotanaka.pokedex.data.repository.base.Resource

interface PokedexRepository {

    suspend fun getGeneration(generation: Int): Resource<PokemonSpecies>

    suspend fun getAllPokemon(): Resource<List<Pokemon>>
    suspend fun getPokemon(id: Int): Resource<Pokemon>
}