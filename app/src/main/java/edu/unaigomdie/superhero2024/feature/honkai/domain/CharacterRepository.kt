package edu.unaigomdie.superhero2024.feature.honkai.domain

interface CharacterRepository {

    suspend fun getCharacters(): List<Honkai>

    suspend fun getCharacter(id: String): Honkai?


}