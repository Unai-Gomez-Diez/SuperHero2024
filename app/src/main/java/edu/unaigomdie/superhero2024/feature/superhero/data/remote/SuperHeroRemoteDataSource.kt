package edu.unaigomdie.superhero2024.feature.superhero.data.remote


import edu.unaigomdie.superhero2024.app.api.ApiClient
import edu.unaigomdie.superhero2024.feature.superhero.domain.SuperHero

class SuperHeroRemoteDataSource {
    val baseUrl = "https://akabab.github.io/superhero-api/api/"
    val apiClient = ApiClient(baseUrl)

    suspend fun getSuperHeroes(): List<SuperHero> {
        apiClient.retrofit
        val response = apiClient.apiService.getHeroes()
        return response.body() ?: emptyList()
    }

    suspend fun getSuperHero(id: String): SuperHero? {
        apiClient.retrofit
        val response = apiClient.apiService.getHero(id)
        return response.body()

    }
}