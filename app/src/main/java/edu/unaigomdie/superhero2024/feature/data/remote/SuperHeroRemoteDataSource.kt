package edu.unaigomdie.superhero2024.feature.data.remote


import edu.unaigomdie.superhero2024.app.api.ApiClient
import edu.unaigomdie.superhero2024.app.api.ApiService
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.domain.SuperHeroRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroRemoteDataSource {
    val apiClient = ApiClient()

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