package edu.unaigomdie.superhero2024.feature.presentation


import android.content.Context
import edu.unaigomdie.superhero2024.feature.data.SuperHeroDataRepository
import edu.unaigomdie.superhero2024.feature.data.local.SuperHeroXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase
import edu.unaigomdie.superhero2024.feature.domain.SetSuperHeroUseCase

class SuperHeroesFactory(context: Context) {
    private val superHeroRemoteDataSource = SuperHeroRemoteDataSource()
    private val superHeroXmlLocalDataSource = SuperHeroXmlLocalDataSource(context)
    private val superHeroDataRepository = SuperHeroDataRepository(
        superHeroRemoteDataSource,
        superHeroXmlLocalDataSource
    )
    private val getSuperHeroUseCase = GetSuperHeroUseCase(superHeroDataRepository)
    private val getSuperHeroesUseCase = GetSuperHeroesUseCase(superHeroDataRepository)
    private val setSuperHeroUseCase = SetSuperHeroUseCase(superHeroDataRepository)

    fun buildViewModel(): SuperHeroesViewModel {
        return SuperHeroesViewModel(
            getSuperHeroesUseCase,
            getSuperHeroUseCase,
            setSuperHeroUseCase
        )
    }
}