package edu.unaigomdie.superhero2024.feature.honkai.domain

interface CharacterRepository {

    suspend fun getCharacters(): List<Character>

    suspend fun getCharacter(id: String): Character?


}