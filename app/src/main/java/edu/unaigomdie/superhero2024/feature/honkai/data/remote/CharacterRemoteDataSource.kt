package edu.unaigomdie.superhero2024.feature.honkai.data.remote

import android.util.Log
import edu.unaigomdie.superhero2024.app.api.ApiClient
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CharacterRemoteDataSource {
    val baseUrl = "https://hsr-api.vercel.app/api/v1/"
    val apiClient = ApiClient(baseUrl)

    suspend fun getCharacters(): List<Character> {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacters()
        //return response.body() ?: emptyList()
        val client = HttpClient(CIO){
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        val response2 = client.get("http://10.0.2.2:8080/characters")
        Log.d("@dev",response2.body<List<Character>>().toString())



        return response.body() ?: emptyList()



    }

    suspend fun getCharacter(id: String): Character? {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacter(id)
        return response.body()
    }
}