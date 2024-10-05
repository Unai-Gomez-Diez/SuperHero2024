package edu.unaigomdie.superhero2024.feature.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.databinding.FragmentSuperHeroBinding
import edu.unaigomdie.superhero2024.feature.presentation.adapter.SuperHeroAdapter
import kotlinx.coroutines.runBlocking

class SuperHeroesActivity : AppCompatActivity() {
    private var _binding: FragmentSuperHeroBinding? = null
    private val binding get() = _binding!!
    private val superheroAdapter = SuperHeroAdapter()


    private lateinit var superHeroesFactory: SuperHeroesFactory
    private lateinit var viewModel: SuperHeroesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentSuperHeroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        superHeroesFactory = SuperHeroesFactory(this)
        viewModel = superHeroesFactory.buildViewModel()

        viewModel.getSuperHeroes()
        setupObservers()
        setupView()



    }

    private fun setupView() {
        binding.apply {
            list.apply {
                layoutManager = GridLayoutManager(
                    this@SuperHeroesActivity,
                    1,
                    LinearLayoutManager.VERTICAL,
                    false
                )

                superheroAdapter.setEvent { superHeroId ->
                    navigateToDetail(superHeroId)
                }
                adapter = this@SuperHeroesActivity.superheroAdapter


            }
        }
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this) {
            uistate ->
            uistate.superHeroes.let {
                superheroAdapter.submitList(it)
            }
            uistate.isLoading.let {
                //progressBar.isVisible = it
            }
            uistate.errorApp?.let {
                showError(it)
            }
        }
    }

    private fun showError(error: ErrorApp) {
        when(error){
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> setContentView(R.layout.internet_error_activity)
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknownErrorApp -> TODO()
        }
    }

    /* private fun bindData(superHeroes: List<SuperHero>) {

         findViewById<TextView>(R.id.id_1).text = superHeroes[0].id.toString()
         findViewById<TextView>(R.id.name_1).text = superHeroes[0].name
         findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
             GlobalScope.launch {
                navigateToDetail(superHeroes[0].id.toString())
             }
         }


         findViewById<TextView>(R.id.id_2).text = superHeroes[1].id.toString()
         findViewById<TextView>(R.id.name_2).text = superHeroes[1].name
         findViewById<LinearLayout>(R.id.layout_2).setOnClickListener {
             GlobalScope.launch {
                 navigateToDetail(superHeroes[1].id.toString())
             }

         }
         findViewById<TextView>(R.id.id_3).text = superHeroes[2].id.toString()
         findViewById<TextView>(R.id.name_3).text = superHeroes[2].name
         findViewById<LinearLayout>(R.id.layout_3).setOnClickListener {
             GlobalScope.launch {
                 navigateToDetail(superHeroes[2].id.toString())
             }

         }

     }*/

    private fun navigateToDetail(superHeroId: String) {
        startActivity(SuperHeroDetailActivity.getIntent(this, superHeroId))
    }
}