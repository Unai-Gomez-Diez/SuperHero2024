package edu.unaigomdie.superhero2024.feature.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import edu.unaigomdie.superhero2024.databinding.FragmentPokemonBinding
import edu.unaigomdie.superhero2024.feature.pokemon.presentation.adapter.PokemonAdapter

class PokemonFragment: Fragment() {
    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!
    private val pokemonAdapter = PokemonAdapter()

    private lateinit var pokemonFactory: PokemonFactory
    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonFactory = PokemonFactory(requireContext())
        viewModel = pokemonFactory.buildViewModel()
        setupObservers()
        viewModel.getPokemons()
    }

    private fun setupView(){
        binding.apply {
            list.apply {
                layoutManager = GridLayoutManager(
                    context,
                    1,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                pokemonAdapter.setEvent { pokemonUrl ->
                    navigateToDetail(pokemonUrl)
                }
                adapter = pokemonAdapter
            }
        }
    }

    fun setupObservers(){
        val observer = Observer<PokemonViewModel.UiState>{
            if (it.isLoading){
                //skeleton.showSkeleton()
            }else{
                pokemonAdapter.submitList(it.pokemon)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)

    }

    private fun navigateToDetail(pokemonUrl: String) {

    }
}