package edu.unaigomdie.superhero2024.feature.domain

class GetSuperHeroesUseCase(private val repository: SuperHeroRepository) {
    suspend operator fun invoke(): List<SuperHero> {
        return repository.getSuperHeroes()
    }
}