package edu.unaigomdie.superhero2024.feature.pokemon.presentation

import android.content.Context
import edu.unaigomdie.superhero2024.feature.pokemon.data.PokemonDataRepository
import edu.unaigomdie.superhero2024.feature.pokemon.data.local.PokemonXmlLocalDataSource
import edu.unaigomdie.superhero2024.feature.pokemon.data.remote.PokemonRemoteDataSource
import edu.unaigomdie.superhero2024.feature.pokemon.domain.GetPokemonUseCase
import edu.unaigomdie.superhero2024.feature.pokemon.domain.GetPokemonsUseCase

class PokemonFactory(context: Context) {
    private val pokemonRemoteDataSource = PokemonRemoteDataSource()
    private val pokemonXmlLocalDataSource = PokemonXmlLocalDataSource(context)
    private val pokemonDataRepository = PokemonDataRepository(
        pokemonRemoteDataSource,
        pokemonXmlLocalDataSource)

    private val getPokemonsUseCase = GetPokemonsUseCase(pokemonDataRepository)
    private val getPokemonUseCase = GetPokemonUseCase(pokemonDataRepository)

    fun buildViewModel(): PokemonViewModel {
        return PokemonViewModel(
            getPokemonsUseCase
        )
    }
}