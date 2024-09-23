package edu.unaigomdie.superhero2024.feature.data.remote


import edu.unaigomdie.superhero2024.app.api.ApiService
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.domain.SuperHeroRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroRemoteDataSource: SuperHeroRepository {

    override suspend fun getSuperHeroes(): List<SuperHero> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://dam.sitehub.es/api-curso/superheroes/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        var apiService: ApiService = retrofit.create(ApiService::class.java)

        val response = apiService.getHeroes()
        return response.body() ?: emptyList()
    }
}