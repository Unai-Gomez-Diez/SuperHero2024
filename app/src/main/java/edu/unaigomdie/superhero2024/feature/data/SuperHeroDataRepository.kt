package edu.unaigomdie.superhero2024.feature.data

import edu.unaigomdie.superhero2024.feature.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val remoteDataSource: SuperHeroRemoteDataSource
): SuperHeroRepository {
    override suspend fun getSuperHeroes(): List<SuperHero> {
        return remoteDataSource.getSuperHeroes()
    }

    override suspend fun getSuperHero(id: String): SuperHero? {
        return remoteDataSource.getSuperHero(id)
    }
}