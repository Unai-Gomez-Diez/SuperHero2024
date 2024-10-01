package edu.unaigomdie.superhero2024.feature.presentation

import androidx.lifecycle.ViewModel
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroDetailViewModel(
    private val getSuperHeroUseCase: GetSuperHeroUseCase
) :
    ViewModel() {

    suspend fun viewCreated(id: String): SuperHero? {
        var superHero: SuperHero? = null

        superHero = getSuperHeroUseCase.invoke(id)
        return superHero
    }


}