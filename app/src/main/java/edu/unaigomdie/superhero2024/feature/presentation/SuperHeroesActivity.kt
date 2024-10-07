package edu.unaigomdie.superhero2024.feature.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.SkeletonLayout
import com.faltenreich.skeletonlayout.applySkeleton
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
    private val skeleton: Skeleton by lazy {
        binding.list.applySkeleton(R.layout.item_superhero,15)
    }

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

        val observer = Observer<SuperHeroesViewModel.UiState> {
            if (it.isLoading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
                if (it.errorApp != null) {
                    showError(it.errorApp)
                }else{
                    superheroAdapter.submitList(it.superHeroes)
                }
            }
        }
        viewModel.uiState.observe(this, observer)


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