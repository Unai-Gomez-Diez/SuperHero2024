package edu.unaigomdie.superhero2024.app.api

import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai
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
    suspend fun getCharacters(): Response<List<Honkai>>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: String): Response<Honkai>

    @GET("pokemon/")
    suspend fun getPokemons(): Response<Page>

    @GET(" ")
    suspend fun getPokemon(): Response<Pokemon>

    @GET(" ")
    suspend fun getMorePokemons(): Response<Page>


}