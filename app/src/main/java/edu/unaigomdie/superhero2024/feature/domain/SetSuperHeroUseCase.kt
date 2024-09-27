package edu.unaigomdie.superhero2024.feature.domain

class SetSuperHeroUseCase(private val repository: SuperHeroRepository) {
    suspend operator fun invoke(superHero: SuperHero) {
        repository.saveSuperHero(superHero)
    }
}