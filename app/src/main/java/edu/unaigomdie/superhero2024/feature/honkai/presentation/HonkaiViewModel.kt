package edu.unaigomdie.superhero2024.feature.honkai.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai
import edu.unaigomdie.superhero2024.feature.honkai.domain.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HonkaiViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {

    private var _uistate = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uistate

     fun getCharacters() {
         _uistate.value = UiState(true)
         viewModelScope.launch(Dispatchers.IO) {
             val characters = getCharactersUseCase()
             _uistate.postValue(UiState(honkai = characters))
         }


    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val honkai: List<Honkai> = emptyList()
    )

}