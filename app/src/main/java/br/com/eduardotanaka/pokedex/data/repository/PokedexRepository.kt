package br.com.eduardotanaka.pokedex.data.repository

import br.com.eduardotanaka.pokedex.data.model.entity.base.PokemonSpecie

interface PokedexRepository {

    suspend fun getGeneration(generation: Int): PokemonSpecie
}