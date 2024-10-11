package edu.unaigomdie.superhero2024.feature.pokemon.data.remote

import edu.unaigomdie.superhero2024.app.api.ApiClient
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Page
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Pokemon

class PokemonRemoteDataSource {
    private val baseUrl = "https://pokeapi.co/api/v2/"
    lateinit var apiClient: ApiClient

    suspend fun getPokemons(): Page {
        apiClient = ApiClient(baseUrl)
        return apiClient.apiService.getPokemons().body()!!
    }

    suspend fun getPokemon(pokemonUrl: String): Pokemon {
        apiClient = ApiClient(pokemonUrl)
        return apiClient.apiService.getPokemon().body()!!

    }

}