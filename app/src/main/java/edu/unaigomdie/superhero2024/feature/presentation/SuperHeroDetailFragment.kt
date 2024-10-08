package edu.unaigomdie.superhero2024.feature.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.app.extension.loadImage
import edu.unaigomdie.superhero2024.databinding.FragmentSuperHeroBinding
import edu.unaigomdie.superhero2024.databinding.FragmentSuperHeroDetailBinding
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroDetailFragment: Fragment() {

    private var _binding: FragmentSuperHeroDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var superHeroesFactory: SuperHeroesFactory
    private lateinit var viewModel: SuperHeroDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperHeroDetailBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        superHeroesFactory = SuperHeroesFactory(requireContext())
        viewModel = superHeroesFactory.buildViewModelDetail()

    }


    private fun getSuperHeroId(): String? {
        return intent.getStringExtra(KEY_SUPER_HERO_ID)
    }


    private fun setupObservers() {
        viewModel.uiState.observe(this) {
                uistate ->
            uistate.superHero?.let {
                bindData(it)
            }
            uistate.isLoading.let {
                //progressBar.isVisible = it
            }
            uistate.errorApp?.let {
                showError(it)
            }
        }
    }

    private fun bindData(superhero: SuperHero) {
        binding.apply {
            image.loadImage(superhero.images.sm)
            nombre.text = superhero.name
            slug.text = superhero.slug
            intelligence.text = superhero.powerstats.intelligence.toString()
            strength.text = superhero.powerstats.strength.toString()
            speed.text = superhero.powerstats.speed.toString()
            durability.text = superhero.powerstats.durability.toString()
            power.text = superhero.powerstats.power.toString()
            combat.text = superhero.powerstats.combat.toString()
            occupation.text = superhero.work.occupation
            base.text = superhero.work.base
            alterEgo.text = superhero.biography.alterEgos
            fullName.text = superhero.biography.fullName
            placeOfBirth.text = superhero.biography.placeOfBirth
        }
    }

    private fun showError(error: ErrorApp) {
        when(error){
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp ->      setError()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknownErrorApp -> TODO()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun setError(){
        setContentView(R.layout.internet_error_activity)
        findViewById<TextView>(R.id.text_view).text = getString(R.string.internet_error_activity_title)
        findViewById<ImageView>(R.id.image_view).setImageResource(R.drawable.ic_internet_error)
    }

    companion object {
        val KEY_SUPER_HERO_ID = "key_super_hero_id"

        fun getIntent(context: Context, superHeroId: String): Intent {

            val intent = Intent(context, SuperHeroDetailActivity::class.java)
            intent.putExtra(KEY_SUPER_HERO_ID, superHeroId)
            return intent
        }
    }
}