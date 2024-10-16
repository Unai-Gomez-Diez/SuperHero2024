package edu.unaigomdie.superhero2024.feature.honkai.data.remote

import android.util.Log
import edu.unaigomdie.superhero2024.app.api.ApiClient
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.content
import io.ktor.client.statement.readRawBytes
import io.ktor.client.statement.request
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class CharacterRemoteDataSource {
    val baseUrl = "https://hsr-api.vercel.app/api/v1/"
    val apiClient = ApiClient(baseUrl)

    @OptIn(InternalAPI::class)
    suspend fun getCharacters(): List<Character> {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacters()
        //return response.body() ?: emptyList()
        val client = HttpClient(){
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }


        val json = Json { ignoreUnknownKeys = true }

        val response2 = client.get("http://10.0.2.2:8080/characters").readRawBytes()
        val jsonString = String(response2, Charsets.UTF_8)
        val characters: List<Character> = json.decodeFromString(jsonString)
        Log.d("@dev", characters.toString())



        return characters ?: emptyList()







    }

    suspend fun getCharacter(id: String): Character? {
        apiClient.retrofit
        val response = apiClient.apiService.getCharacter(id)
        return response.body()
    }
}