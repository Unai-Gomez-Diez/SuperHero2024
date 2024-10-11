package edu.unaigomdie.superhero2024.feature.pokemon.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unaigomdie.superhero2024.feature.pokemon.domain.GetPokemonUseCase
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Pokemon
import edu.unaigomdie.superhero2024.feature.pokemon.domain.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel() {
    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getPokemon(pokemonUrl: String){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO){
            val pokemon = getPokemonUseCase(pokemonUrl)
            _uiState.postValue(UiState(pokemon = pokemon))
        }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val pokemon: Pokemon? = null,
        val error: Throwable? = null
    )
}