package edu.unaigomdie.superhero2024.app.api

import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("all.json")
    suspend fun getHeroes(): Response<List<SuperHero>>

    @GET("id/{id}.json")
    suspend fun getHero(@Path("id") id: String): Response<SuperHero>

}