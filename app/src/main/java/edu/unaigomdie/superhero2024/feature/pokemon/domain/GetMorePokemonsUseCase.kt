package edu.unaigomdie.superhero2024.feature.pokemon.domain

class GetMorePokemonsUseCase(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(): Page {
        return pokemonRepository.getMorePokemons()
    }

}