package edu.unaigomdie.superhero2024.feature.honkai.data.remote

import edu.unaigomdie.superhero2024.app.api.ApiClient
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.InternalAPI

class HonkaiRemoteDataSource {
    val baseUrl = "https://hsr-api.vercel.app/api/v1/"
    val apiClient = ApiClient(baseUrl)

    @OptIn(InternalAPI::class)
    suspend fun getCharacters(): List<Honkai> {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacters()
        //return response.body() ?: emptyList()
        val client = HttpClient(CIO){
            install(ContentNegotiation) {
                json()
            }
        }

        val response2: List<Honkai> = client.get("http://10.0.2.2:8080/characters").body()
        return response2
    }


    suspend fun getCharacter(id: String): Honkai? {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacter(id)
        return response.body()
    }
}