package edu.unaigomdie.superhero2024.feature.data

import edu.unaigomdie.superhero2024.feature.data.local.SuperHeroXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val remoteDataSource: SuperHeroRemoteDataSource,
    private val localDataSource: SuperHeroXmlLocalDataSource
): SuperHeroRepository {
    override suspend fun getSuperHeroes(): List<SuperHero> {
        val localSuperHeroes = localDataSource.getSuperHeroes()
        if (localSuperHeroes == null){
            val remoteSuperHeroes = remoteDataSource.getSuperHeroes()
            localDataSource.saveSuperHeroes(remoteSuperHeroes)
            return remoteSuperHeroes
        }
        return localSuperHeroes
    }

    override suspend fun getSuperHero(id: String): SuperHero? {
        val localSuperHero = localDataSource.getSuperHero()
        if (localSuperHero == null || localSuperHero.id.toString() != id) {
            val remoteSuperHero = remoteDataSource.getSuperHero(id)
            remoteSuperHero?.let {
                localDataSource.saveSuperHero(it)
            }
            return remoteSuperHero
        }
        return localSuperHero
    }

    override suspend fun saveSuperHero(superHero: SuperHero) {
        localDataSource.saveSuperHero(superHero)
    }
}