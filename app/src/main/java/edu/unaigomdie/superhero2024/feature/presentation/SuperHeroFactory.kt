package edu.unaigomdie.superhero2024.feature.presentation


import android.content.Context
import edu.unaigomdie.superhero2024.feature.data.SuperHeroDataRepository
import edu.unaigomdie.superhero2024.feature.data.local.SuperHeroXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase
import edu.unaigomdie.superhero2024.feature.domain.SetSuperHeroUseCase

class SuperHeroFactory(context: Context) {
    private val superHeroDataRepository = SuperHeroDataRepository(SuperHeroRemoteDataSource(),
        SuperHeroXmlLocalDataSource(context)
    )
    fun buildViewModel(): SuperHeroViewModel {
        return SuperHeroViewModel(
            GetSuperHeroesUseCase(superHeroDataRepository),
            GetSuperHeroUseCase(superHeroDataRepository),
            SetSuperHeroUseCase(superHeroDataRepository)
        )
    }
}