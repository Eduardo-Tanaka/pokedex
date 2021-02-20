package br.com.eduardotanaka.pokedex.constants

enum class CacheKey {

    POKEDEX_GENERATION;

    override fun toString(): String {
        return name
    }
}
