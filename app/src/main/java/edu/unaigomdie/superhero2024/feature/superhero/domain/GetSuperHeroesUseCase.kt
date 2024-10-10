package edu.unaigomdie.superhero2024.feature.superhero.domain

class GetSuperHeroesUseCase(private val repository: SuperHeroRepository) {
    suspend operator fun invoke(): List<SuperHero> {
        return repository.getSuperHeroes()
    }
}