package edu.unaigomdie.superhero2024.feature.domain

class GetSuperHerosUseCase(private val repository: SuperHeroRepository) {
    fun invoke(): List<SuperHero> {
        return repository.getSuperHeros()

    }
}