package edu.unaigomdie.superhero2024.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.unaigomdie.superhero2024.feature.domain.GetSuperHeroesUseCase
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SuperHeroesViewModel(
    private val getSuperHeroesUseCase: GetSuperHeroesUseCase
) :
    ViewModel() {

    private val _uistate = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uistate

    suspend fun getSuperHeroes() {
        GlobalScope.launch(Dispatchers.IO){
            UiState(superHeroes = getSuperHeroesUseCase.invoke())
        }



    }

    data class UiState(
        val superHeroes: List<SuperHero> = emptyList()
    )



}