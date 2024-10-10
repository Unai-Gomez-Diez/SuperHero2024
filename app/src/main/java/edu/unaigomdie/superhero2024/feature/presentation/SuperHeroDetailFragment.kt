package edu.unaigomdie.superhero2024.feature.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        superHeroesFactory = SuperHeroesFactory(requireContext())
        viewModel = superHeroesFactory.buildViewModelDetail()
        setupObservers()

        val superHeroId = SuperHeroDetailFragmentArgs.fromBundle(requireArguments()).superHeroId
        viewModel.viewCreated(superHeroId)

    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
                uistate ->
            uistate.superHero?.let {
                bindData(it)
            }
            uistate.isLoading.let {

            }
            uistate.errorApp?.let {
                showError(it)
            }
        }
    }

    private fun bindData(superhero: SuperHero) {
        binding.detailToolbar.back.setOnClickListener{
            Navigation.findNavController(this.requireView()).navigate(R.id.action_superheroes_detail_fragment_to_superheroes_fragment)
        }
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
      //  setContentView(R.layout.internet_error_activity)
        binding.apply {
            image.setImageResource(R.drawable.ic_internet_error)
            nombre.text = getString(R.string.internet_error_activity_title)
        }
    }


}