package edu.unaigomdie.superhero2024.feature.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import kotlinx.coroutines.runBlocking

class SuperHeroActivity : AppCompatActivity() {

    private val superHeroFactory = SuperHeroFactory()
    private val viewModel = superHeroFactory.buildViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        runBlocking {
            val superHeros = viewModel.getSuperHeroes()
            Log.d("@dev", superHeros.toString())

            val superHero = viewModel.getSuperHero("2")
            Log.d("@dev", superHero.toString())
        }

    }

    private fun bindData(superHeroes: List<SuperHero>) {

        findViewById<TextView>(R.id.id_1).text = superHeroes[0].id.toString()
        findViewById<TextView>(R.id.name_1).text = superHeroes[0].name
        findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
            val superHero = viewModel.getSuperHero(superHeroes[0].id.toString())
        }

        findViewById<TextView>(R.id.id_2).text = superHeroes[1].id.toString()
        findViewById<TextView>(R.id.name_2).text = superHeroes[1].name

        findViewById<TextView>(R.id.id_3).text = superHeroes[2].id.toString()
        findViewById<TextView>(R.id.name_3).text = superHeroes[2].name

    }
}