package edu.unaigomdie.superhero2024.feature.data

import edu.unaigomdie.superhero2024.feature.data.local.SuperHeroXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val remoteDataSource: SuperHeroRemoteDataSource,
    private val localDataSource: SuperHeroXmlLocalDataSource
) : SuperHeroRepository {
    override suspend fun getSuperHeroes(): List<SuperHero> {
        val localSuperHeroes = localDataSource.getSuperHeroes()
        return if (localSuperHeroes.isEmpty()) {
            val remoteSuperHeroes = remoteDataSource.getSuperHeroes()
            localDataSource.deleteSuperHeroes()
            localDataSource.saveSuperHeroes(remoteSuperHeroes)
            remoteSuperHeroes
        } else {
            localSuperHeroes
        }

    }

    override suspend fun getSuperHero(id: String): SuperHero? {
        val localSuperHero = localDataSource.getById(id)
        return if (localSuperHero == null || localSuperHero.id.toString() != id) {
            localDataSource.deleteSuperHeroes()
            val remoteSuperHero = remoteDataSource.getSuperHero(id)
            remoteSuperHero?.let {
                localDataSource.saveSuperHero(it)
            }
            remoteSuperHero
        } else {
            localSuperHero
        }

    }

    override suspend fun saveSuperHero(superHero: SuperHero) {
        localDataSource.saveSuperHero(superHero)
    }
}