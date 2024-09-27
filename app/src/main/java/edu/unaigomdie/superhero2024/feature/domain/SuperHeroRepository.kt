package edu.unaigomdie.superhero2024.feature.domain

interface SuperHeroRepository {
    suspend fun getSuperHeroes(): List<SuperHero>

    suspend fun getSuperHero(id: String): SuperHero?

    suspend fun saveSuperHero(superHero: SuperHero)

}