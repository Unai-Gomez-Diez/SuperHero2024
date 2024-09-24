package edu.unaigomdie.superhero2024.app.api

import edu.unaigomdie.superhero2024.feature.domain.Biography
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.domain.Work
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response

interface ApiService {
    @GET("all.json")
    suspend fun getHeroes(): Response<List<SuperHero>>

    @GET("id/{id}.json")
    suspend fun getHero(@Path("id") id: String): Response<SuperHero>



}