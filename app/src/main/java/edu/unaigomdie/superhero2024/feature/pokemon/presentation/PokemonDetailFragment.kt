package edu.unaigomdie.superhero2024.feature.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import edu.unaigomdie.superhero2024.databinding.FragmentPokemonDetailBinding
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Pokemon

class PokemonDetailFragment: Fragment() {
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var pokemonFactory: PokemonFactory
    private lateinit var viewModel: PokemonDetailViewModel

    val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonFactory = PokemonFactory(requireContext())
        viewModel = pokemonFactory.buildDetailViewModel()
        setupObservers()
        val pokemonUrl = args.pokemonUrl
        viewModel.getPokemon(pokemonUrl)
    }

    private fun setupObservers(){
        viewModel.uiState.observe(viewLifecycleOwner){
            uistate ->
            uistate.pokemon?.let {
                bindData(it)
            }
            uistate.isLoading.let {

            }
            uistate.error?.let {
               // showError(it)
            }
        }
    }

    private fun bindData(pokemon: Pokemon) {
        binding.apply {
            id.text = pokemon.id.toString()
            name.text = pokemon.name
        }
    }
}