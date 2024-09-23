package edu.unaigomdie.superhero2024.feature.presentation

import edu.unaigomdie.superhero2024.feature.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase

class SuperHeroFactory {
    fun buildViewModel(): SuperHeroViewModel {
        return SuperHeroViewModel(GetSuperHeroesUseCase(SuperHeroRemoteDataSource()))
    }
}