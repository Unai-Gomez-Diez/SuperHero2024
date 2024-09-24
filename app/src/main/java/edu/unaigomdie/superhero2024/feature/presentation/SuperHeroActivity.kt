package edu.unaigomdie.superhero2024.feature.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.presentation.adapter.SuperHeroAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SuperHeroActivity : AppCompatActivity() {

    private val superHeroFactory = SuperHeroFactory()
    private val viewModel = superHeroFactory.buildViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        runBlocking {
            val superHeros = viewModel.getSuperHeroes()
            val adapter = SuperHeroAdapter()
            TODO("add adapter")
            //  Log.d("@dev", superHeros.toString())

            //     val superHero = viewModel.getSuperHero("2")
            // Log.d("@dev", superHero.toString())
            bindData(superHeros)
        }


    }

    private fun bindData(superHeroes: List<SuperHero>) {

        findViewById<TextView>(R.id.id_1).text = superHeroes[0].id.toString()
        findViewById<TextView>(R.id.name_1).text = superHeroes[0].name
        findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
            GlobalScope.launch {
                val superHero1 = viewModel.getSuperHero(superHeroes[0].id.toString())
                superHero1?.let {
                    Log.d("@dev", it.toString())
                }
            }
        }


        findViewById<TextView>(R.id.id_2).text = superHeroes[1].id.toString()
        findViewById<TextView>(R.id.name_2).text = superHeroes[1].name
        findViewById<LinearLayout>(R.id.layout_2).setOnClickListener {
            GlobalScope.launch {
                val superHero2 = viewModel.getSuperHero(superHeroes[1].id.toString())
                superHero2?.let {
                    Log.d("@dev", it.toString())
                }
            }

        }
        findViewById<TextView>(R.id.id_3).text = superHeroes[2].id.toString()
        findViewById<TextView>(R.id.name_3).text = superHeroes[2].name
        findViewById<LinearLayout>(R.id.layout_3).setOnClickListener {
            GlobalScope.launch {
                val superHero3 = viewModel.getSuperHero(superHeroes[2].id.toString())
                superHero3?.let {
                    Log.d("@dev", it.toString())
                }
            }

        }

    }
}