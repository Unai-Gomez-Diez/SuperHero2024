package edu.unaigomdie.superhero2024.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroUseCase
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SuperHeroDetailViewModel(
    private val getSuperHeroUseCase: GetSuperHeroUseCase
) :
    ViewModel() {
        private val _uistate = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uistate

    fun viewCreated(id: String){
        viewModelScope.launch(Dispatchers.IO){
            _uistate.postValue(UiState(getSuperHeroUseCase(id)))
        }

    }

    data class UiState(
        val superHero: SuperHero? = null
    )



}