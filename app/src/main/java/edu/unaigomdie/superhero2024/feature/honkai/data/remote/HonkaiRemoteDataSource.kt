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

    val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getCharacters(): List<Honkai> {
        val response2: List<Honkai> = client.get("http://10.0.2.2:8080/characters").body()
        return response2
    }


    suspend fun getCharacter(id: String): Honkai {
        val response2: Honkai = client.get("http://10.0.2.2:8080/characters/$id").body()
        return response2
    }
}