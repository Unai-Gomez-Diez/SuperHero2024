package edu.unaigomdie.superhero2024.feature.honkai.presentation

import android.content.Context
import edu.unaigomdie.superhero2024.feature.honkai.data.CharacterDataRepository
import edu.unaigomdie.superhero2024.feature.honkai.data.local.CharacterXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.honkai.data.remote.CharacterRemoteDataSource
import edu.unaigomdie.superhero2024.feature.honkai.domain.GetCharacterUseCase
import edu.unaigomdie.superhero2024.feature.honkai.domain.GetCharactersUseCase

class HonkaiFactory(context: Context) {
    private val characterRemoteDataSource = CharacterRemoteDataSource()
    private val characterXmlLocalDataSource = CharacterXmlLocalDataSource(context)
    private val characterDataRepository = CharacterDataRepository(
        characterRemoteDataSource,
        characterXmlLocalDataSource)
    private val getCharactersUseCase = GetCharactersUseCase(characterDataRepository)
    private val getCharacterUseCase = GetCharacterUseCase(characterDataRepository)

    fun buildViewModel(): HonkaiViewModel {
        return HonkaiViewModel(
            getCharactersUseCase
        )
    }

    fun buildViewModelDetail(): HonkaiDetailViewModel {
        return HonkaiDetailViewModel(
            getCharacterUseCase
        )
    }
}