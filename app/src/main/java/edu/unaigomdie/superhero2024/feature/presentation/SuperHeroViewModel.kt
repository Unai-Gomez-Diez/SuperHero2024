package edu.unaigomdie.superhero2024.feature.presentation

import androidx.lifecycle.ViewModel
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroViewModel(private val getSuperHeroesUseCase: GetSuperHeroesUseCase):
ViewModel(){
    suspend fun getSuperHeroes(): List<SuperHero>{
        return getSuperHeroesUseCase.invoke()
    }
}