package edu.unaigomdie.superhero2024.feature.honkai.domain

class GetCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(): List<Character> {
        return repository.getCharacters()
    }

}