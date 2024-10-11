package edu.unaigomdie.superhero2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationBarView

class SuperHeroesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superheroes)
        val navigationBar = findViewById<NavigationBarView>(R.id.bottom_navigation)
        navigationBar.setOnItemSelectedListener { item ->
            val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

            when (item.itemId) {
                R.id.item_1 -> {
                    if (navController.currentDestination?.id == R.id.honkai_fragment) {
                        navController.navigate(R.id.action_honkai_fragment_to_superheroes_fragment)
                    }else{
                        navController.navigate(R.id.pokemon_to_superhero)
                    }
                    true
                }
                R.id.item_2 -> {
                    if (navController.currentDestination?.id == R.id.superheroes_fragment) {
                        navController.navigate(R.id.action_superheroes_fragment_to_honkai_fragment)
                    }else{
                        navController.navigate(R.id.pokemon_to_honkai)
                    }
                    true
                }
                R.id.item_3 -> {
                    if (navController.currentDestination?.id == R.id.superheroes_fragment) {
                        navController.navigate(R.id.action_superheroes_fragment_to_pokemon_fragment)
                    }else{
                        navController.navigate(R.id.action_honkai_fragment_to_pokemon_fragment)
                    }
                    true
                }
                else -> false
            }
        }
    }
}