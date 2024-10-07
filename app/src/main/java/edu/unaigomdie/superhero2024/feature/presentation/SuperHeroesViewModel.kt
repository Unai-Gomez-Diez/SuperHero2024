package edu.unaigomdie.superhero2024.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuperHeroesViewModel(
    private val getSuperHeroesUseCase: GetSuperHeroesUseCase
) :
    ViewModel() {

    private val _uistate = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uistate

    fun getSuperHeroes() {
        _uistate.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO){
            delay(2000)
            val superHeroes = getSuperHeroesUseCase()
            _uistate.postValue(UiState(superHeroes = superHeroes))
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val superHeroes: List<SuperHero> = emptyList()
    )
}