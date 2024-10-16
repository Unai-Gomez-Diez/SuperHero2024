package edu.unaigomdie.superhero2024.feature.honkai.presentation

import android.content.Context
import edu.unaigomdie.superhero2024.feature.honkai.data.HonkaiDataRepository
import edu.unaigomdie.superhero2024.feature.honkai.data.local.HonkaiXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.honkai.data.remote.HonkaiRemoteDataSource
import edu.unaigomdie.superhero2024.feature.honkai.domain.GetCharacterUseCase
import edu.unaigomdie.superhero2024.feature.honkai.domain.GetCharactersUseCase

class HonkaiFactory(context: Context) {
    private val honkaiRemoteDataSource = HonkaiRemoteDataSource()
    private val honkaiXmlLocalDataSource = HonkaiXmlLocalDataSource(context)
    private val honkaiDataRepository = HonkaiDataRepository(
        honkaiRemoteDataSource,
        honkaiXmlLocalDataSource)
    private val getCharactersUseCase = GetCharactersUseCase(honkaiDataRepository)
    private val getCharacterUseCase = GetCharacterUseCase(honkaiDataRepository)

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