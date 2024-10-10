package edu.unaigomdie.superhero2024.feature.superhero.presentation


import android.content.Context
import edu.unaigomdie.superhero2024.feature.superhero.data.SuperHeroDataRepository
import edu.unaigomdie.superhero2024.feature.superhero.data.local.SuperHeroXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.superhero.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.superhero.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.superhero.domain.GetSuperHeroesUseCase

class SuperHeroesFactory(context: Context) {
    private val superHeroRemoteDataSource = SuperHeroRemoteDataSource()
    private val superHeroXmlLocalDataSource = SuperHeroXmlLocalDataSource(context)
    private val superHeroDataRepository = SuperHeroDataRepository(
        superHeroRemoteDataSource,
        superHeroXmlLocalDataSource
    )
    private val getSuperHeroUseCase = GetSuperHeroUseCase(superHeroDataRepository)
    private val getSuperHeroesUseCase = GetSuperHeroesUseCase(superHeroDataRepository)


    fun buildViewModel(): SuperHeroesViewModel {
        return SuperHeroesViewModel(
            getSuperHeroesUseCase

        )
    }

    fun buildViewModelDetail(): SuperHeroDetailViewModel {
        return SuperHeroDetailViewModel(
            getSuperHeroUseCase,
        )
    }
}