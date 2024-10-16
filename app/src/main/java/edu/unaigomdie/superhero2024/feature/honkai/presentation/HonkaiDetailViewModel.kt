package edu.unaigomdie.superhero2024.feature.honkai.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai
import edu.unaigomdie.superhero2024.feature.honkai.domain.GetCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HonkaiDetailViewModel(private val getCharacterUseCase: GetCharacterUseCase): ViewModel() {

    private var _uistate = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uistate

    fun getCharacter(characterId: String) {
        _uistate.value = UiState(true)
        viewModelScope.launch(Dispatchers.IO) {
            val character = getCharacterUseCase(characterId)
            _uistate.postValue(UiState(honkai = character))
        }

    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val honkai: Honkai? = null
    )

}