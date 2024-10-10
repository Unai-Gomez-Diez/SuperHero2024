package edu.unaigomdie.superhero2024.feature.superhero.domain

interface SuperHeroRepository {
    suspend fun getSuperHeroes(): List<SuperHero>

    suspend fun getSuperHero(id: String): SuperHero?



}