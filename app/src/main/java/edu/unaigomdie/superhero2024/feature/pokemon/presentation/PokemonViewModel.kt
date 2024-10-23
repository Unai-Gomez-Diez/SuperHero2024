package edu.unaigomdie.superhero2024.feature.pokemon.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unaigomdie.superhero2024.feature.pokemon.domain.GetMorePokemonsUseCase
import edu.unaigomdie.superhero2024.feature.pokemon.domain.GetPokemonsUseCase
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val getPokemonsUseCase: GetPokemonsUseCase,
                       private val getMorePokemonsUseCase: GetMorePokemonsUseCase
)
    : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getPokemons() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val pokemon = getPokemonsUseCase()
            _uiState.postValue(UiState(pokemon =pokemon.results))
        }
    }

    fun getMorePokemons() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val pokemon = getMorePokemonsUseCase()
            _uiState.postValue(UiState(pokemon =pokemon.results))
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val pokemon: List<Results>? = emptyList(),
        val error: String? = null
    )
}