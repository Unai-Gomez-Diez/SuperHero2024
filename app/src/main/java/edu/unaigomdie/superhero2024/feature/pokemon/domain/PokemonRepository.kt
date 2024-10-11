package edu.unaigomdie.superhero2024.feature.pokemon.domain

interface PokemonRepository {

    suspend fun getPokemons(): Page

    suspend fun getPokemon(pokemonUrl: String): Pokemon
}