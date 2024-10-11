package edu.unaigomdie.superhero2024.app.api

import edu.unaigomdie.superhero2024.feature.honkai.domain.Character
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Page
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Pokemon
import edu.unaigomdie.superhero2024.feature.superhero.domain.SuperHero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("all.json")
    suspend fun getHeroes(): Response<List<SuperHero>>

    @GET("id/{id}.json")
    suspend fun getHero(@Path("id") id: String): Response<SuperHero>

    @GET("characters")
    suspend fun getCharacters(): Response<List<Character>>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: String): Response<Character>

    @GET("")
    suspend fun getPokemons(): Response<Page>

    @GET("")
    suspend fun getPokemon(): Response<Pokemon>


}