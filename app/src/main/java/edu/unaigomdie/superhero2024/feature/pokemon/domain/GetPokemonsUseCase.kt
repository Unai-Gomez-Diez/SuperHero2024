package edu.unaigomdie.superhero2024.feature.pokemon.domain

class GetPokemonsUseCase(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(): Page {
        return pokemonRepository.getPokemons()
    }
}