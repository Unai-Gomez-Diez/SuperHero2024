package edu.unaigomdie.superhero2024.app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiService: ApiService = retrofit.create(ApiService::class.java)
}