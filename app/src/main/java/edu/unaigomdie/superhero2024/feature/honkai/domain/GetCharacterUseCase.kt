package edu.unaigomdie.superhero2024.feature.honkai.domain

class GetCharacterUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(id: String): Character? {
        return repository.getCharacter(id)
    }
}