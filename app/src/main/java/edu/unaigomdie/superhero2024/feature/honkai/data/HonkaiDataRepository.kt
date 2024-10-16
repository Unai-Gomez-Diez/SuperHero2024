package edu.unaigomdie.superhero2024.feature.honkai.data

import edu.unaigomdie.superhero2024.feature.honkai.data.local.HonkaiXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.honkai.data.remote.HonkaiRemoteDataSource
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai
import edu.unaigomdie.superhero2024.feature.honkai.domain.CharacterRepository

class HonkaiDataRepository(
    private val remoteDataSource: HonkaiRemoteDataSource,
    private val localDataSource: HonkaiXmlLocalDataSource
)
    : CharacterRepository {
    override suspend fun getCharacters(): List<Honkai> {
        val characters = localDataSource.getCharacters()
        if (characters.isEmpty()) {
            val remoteCharacters = remoteDataSource.getCharacters()
            localDataSource.deleteCharacters()
            localDataSource.saveCharacters(remoteCharacters)
            return remoteCharacters
        }
        return characters
    }

    override suspend fun getCharacter(id: String): Honkai? {
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