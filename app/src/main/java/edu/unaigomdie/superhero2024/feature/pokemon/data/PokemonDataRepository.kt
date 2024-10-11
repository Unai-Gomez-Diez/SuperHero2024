package edu.unaigomdie.superhero2024.feature.pokemon.data

import edu.unaigomdie.superhero2024.feature.pokemon.data.local.PokemonXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.pokemon.data.remote.PokemonRemoteDataSource
import edu.unaigomdie.superhero2024.feature.pokemon.domain.PokemonRepository
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Page
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Pokemon

class PokemonDataRepository(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonXmlLocalDataSource
): PokemonRepository {
    override suspend fun getPokemons(): Page {
        val page = localDataSource.getPage()
        return if (page != null) {
            val pageRemote = remoteDataSource.getPokemons()
            localDataSource.savePage(pageRemote)
            pageRemote
        }else{
            page!!
        }

    }

    override suspend fun getPokemon(pokemonUrl: String): Pokemon {
        val pokemon = localDataSource.getPokemon(pokemonUrl)
        return if (pokemon != null) {
            val pokemonRemote = remoteDataSource.getPokemon(pokemonUrl)
            localDataSource.savePokemon(pokemonRemote)
            pokemonRemote
        }else{
            pokemon!!
        }
    }

}