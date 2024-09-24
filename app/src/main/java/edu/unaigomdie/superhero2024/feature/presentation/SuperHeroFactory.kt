package edu.unaigomdie.superhero2024.feature.presentation

import edu.unaigomdie.superhero2024.feature.data.SuperHeroDataRepository
import edu.unaigomdie.superhero2024.feature.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase

class SuperHeroFactory {
    private val superHeroDataRepository = SuperHeroDataRepository(SuperHeroRemoteDataSource())
    fun buildViewModel(): SuperHeroViewModel {
        return SuperHeroViewModel(
            GetSuperHeroesUseCase(superHeroDataRepository),
            GetSuperHeroUseCase(superHeroDataRepository)
        )
    }
}