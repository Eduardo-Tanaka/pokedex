package br.com.eduardotanaka.pokedex.data.repository

import br.com.eduardotanaka.pokedex.data.model.entity.base.PokemonSpecies
import br.com.eduardotanaka.pokedex.data.repository.base.Resource

interface PokedexRepository {

    suspend fun getGeneration(generation: Int): Resource<PokemonSpecies>
}