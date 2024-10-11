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
        navigationBar.setOnItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.item_1 -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_honkai_fragment_to_superheroes_fragment)
                    true
                }
                R.id.item_2 -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_superheroes_fragment_to_honkai_fragment)
                    true
                }
                else -> false
            }
        }
    }
}