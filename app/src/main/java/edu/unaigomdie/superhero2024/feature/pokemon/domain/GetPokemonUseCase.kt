package edu.unaigomdie.superhero2024.feature.pokemon.domain

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(pokemonUrl: String): Pokemon {
        return pokemonRepository.getPokemon(pokemonUrl)
    }

}