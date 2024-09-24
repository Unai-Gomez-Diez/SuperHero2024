package edu.unaigomdie.superhero2024.feature.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.unaigomdie.superhero2024.R
import kotlinx.coroutines.runBlocking

class SuperHeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = SuperHeroFactory().buildViewModel()
        runBlocking {
            val superHeros = viewModel.getSuperHeroes()
            Log.d("@dev", superHeros.toString())

            val superHero = viewModel.getSuperHero("2")
            Log.d("@dev", superHero.toString())

        }

    }
}