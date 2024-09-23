package edu.unaigomdie.superhero2024.feature.domain

class GetSuperHeroesUseCase(private val repository: SuperHeroRepository) {
    suspend fun invoke(): List<SuperHero> {
        return repository.getSuperHeroes()

    }
}