package edu.unaigomdie.superhero2024.feature.superhero.data

import edu.unaigomdie.superhero2024.feature.superhero.data.local.SuperHeroXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.superhero.data.remote.SuperHeroRemoteDataSource
import edu.unaigomdie.superhero2024.feature.superhero.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.superhero.domain.SuperHeroRepository

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
}