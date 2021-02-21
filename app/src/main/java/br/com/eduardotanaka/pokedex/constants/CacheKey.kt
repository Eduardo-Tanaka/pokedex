package br.com.eduardotanaka.pokedex.constants

enum class CacheKey {

    POKEDEX_POKEMON,
    POKEDEX_POKEMON_LIST,
    POKEDEX_GENERATION;

    override fun toString(): String {
        return name
    }
}
