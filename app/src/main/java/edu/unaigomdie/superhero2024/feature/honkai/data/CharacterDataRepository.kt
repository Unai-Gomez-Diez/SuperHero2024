package edu.unaigomdie.superhero2024.feature.honkai.data

import edu.unaigomdie.superhero2024.feature.honkai.data.local.CharacterXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.honkai.data.remote.CharacterRemoteDataSource
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character
import edu.unaigomdie.superhero2024.feature.honkai.domain.CharacterRepository

class CharacterDataRepository(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterXmlLocalDataSource
)
    : CharacterRepository {
    override suspend fun getCharacters(): List<Character> {
        val characters = localDataSource.getCharacters()
        if (characters.isEmpty()) {
            val remoteCharacters = remoteDataSource.getCharacters()
            localDataSource.deleteCharacters()
            localDataSource.saveCharacters(remoteCharacters)
            return remoteCharacters
        }
        return characters
    }

    override suspend fun getCharacter(id: String): Character? {
        val character = localDataSource.getCharacter(id)
        if (character == null){
            localDataSource.deleteCharacters()
            val remoteCharacter = remoteDataSource.getCharacter(id)
            remoteCharacter?.let {
                localDataSource.saveCharacter(it)
            }
            return remoteCharacter
        }
        return character
    }
}