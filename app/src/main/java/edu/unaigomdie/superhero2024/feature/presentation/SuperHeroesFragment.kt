package edu.unaigomdie.superhero2024.feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.databinding.FragmentSuperHeroBinding
import edu.unaigomdie.superhero2024.feature.presentation.adapter.SuperHeroAdapter

class SuperHeroesFragment: Fragment() {
    private var _binding: FragmentSuperHeroBinding? = null
    private val binding get() = _binding!!
    private val superheroAdapter = SuperHeroAdapter()

    private lateinit var superHeroesFactory: SuperHeroesFactory
    private lateinit var viewModel: SuperHeroesViewModel
    private val skeleton: Skeleton by lazy {
        binding.list.applySkeleton(R.layout.item_superhero,15)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperHeroBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        superHeroesFactory = SuperHeroesFactory(requireContext())
        viewModel = superHeroesFactory.buildViewModel()
        setupObservers()

        viewModel.getSuperHeroes()
    }

    private fun setupView() {
        binding.apply {
            list.apply {
                layoutManager = GridLayoutManager(
                    context,
                    1,
                    LinearLayoutManager.VERTICAL,
                    false
                )

                superheroAdapter.setEvent { superHeroId ->
                    navigateToDetail(superHeroId)
                }
                adapter = superheroAdapter
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
        viewModel.uiState.observe(viewLifecycleOwner, observer)


    }

    private fun navigateToDetail(superHeroId: String) {
        findNavController().navigate(SuperHeroesFragmentDirections.actionSuperheroesFragmentToSuperheroesDetailFragment(superHeroId))
    }

    private fun showError(error: ErrorApp) {
        when(error){
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> TODO()//setContentView(R.layout.internet_error_activity)
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknownErrorApp -> TODO()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}