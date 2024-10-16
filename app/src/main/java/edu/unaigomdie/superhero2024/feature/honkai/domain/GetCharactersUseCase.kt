package edu.unaigomdie.superhero2024.feature.honkai.domain

class GetCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(): List<Honkai> {
        return repository.getCharacters()
    }

}