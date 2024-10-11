package edu.unaigomdie.superhero2024.feature.honkai.data.remote

import edu.unaigomdie.superhero2024.app.api.ApiClient
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character

class CharacterRemoteDataSource {
    val baseUrl = "https://hsr-api.vercel.app/api/v1/"
    val apiClient = ApiClient(baseUrl)

    suspend fun getCharacters(): List<Character> {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacters()
        return response.body() ?: emptyList()
    }

    suspend fun getCharacter(id: String): Character? {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacter(id)
        return response.body()
    }
}