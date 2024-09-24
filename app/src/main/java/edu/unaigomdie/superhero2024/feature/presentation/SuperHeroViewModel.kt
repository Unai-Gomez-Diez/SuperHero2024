package edu.unaigomdie.superhero2024.feature.presentation

import androidx.lifecycle.ViewModel
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroViewModel(private val getSuperHeroesUseCase: GetSuperHeroesUseCase,
    private val getSuperHeroUseCase: GetSuperHeroUseCase
):
ViewModel(){
    suspend fun getSuperHeroes(): List<SuperHero>{
        return getSuperHeroesUseCase.invoke()
    }

    suspend fun getSuperHero(id: String): SuperHero?{
        var superHero: SuperHero? = null

        superHero = getSuperHeroUseCase.invoke(id)



        return superHero
    }
}