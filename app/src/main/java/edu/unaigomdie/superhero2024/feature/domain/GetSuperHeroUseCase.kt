package edu.unaigomdie.superhero2024.feature.domain

class GetSuperHeroUseCase(private val repository: SuperHeroRepository) {
    suspend operator fun invoke(id: String): SuperHero? {
        return repository.getSuperHero(id)

    }
}