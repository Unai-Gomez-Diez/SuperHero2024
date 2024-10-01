package edu.unaigomdie.superhero2024.feature.presentation

import androidx.lifecycle.ViewModel
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase
import edu.unaigomdie.superhero2024.feature.domain.SetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroesViewModel(
    private val getSuperHeroesUseCase: GetSuperHeroesUseCase,
    private val getSuperHeroUseCase: GetSuperHeroUseCase,
    private val setSuperHeroUseCase: SetSuperHeroUseCase
) :
    ViewModel() {
    suspend fun getSuperHeroes(): List<SuperHero> {
        return getSuperHeroesUseCase.invoke()
    }

    suspend fun getSuperHero(id: String): SuperHero? {
        var superHero: SuperHero? = null

        superHero = getSuperHeroUseCase.invoke(id)
        return superHero
    }

    suspend fun setSuperHero(superHero: SuperHero) {
        setSuperHeroUseCase.invoke(superHero)
    }

}