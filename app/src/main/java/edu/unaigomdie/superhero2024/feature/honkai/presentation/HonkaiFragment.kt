package edu.unaigomdie.superhero2024.feature.honkai.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.databinding.FragmentHonkaiBinding
import edu.unaigomdie.superhero2024.feature.honkai.presentation.adapter.HonkaiAdapter

class HonkaiFragment: Fragment() {
    private var _binding: FragmentHonkaiBinding? = null
    private val binding get() = _binding!!
    private val honkaiAdapter = HonkaiAdapter()

    private lateinit var honkaiFactory: HonkaiFactory
    private lateinit var viewModel: HonkaiViewModel
    private val skeleton: Skeleton by lazy {
        binding.list.applySkeleton(R.layout.item_honkai,15)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHonkaiBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        honkaiFactory = HonkaiFactory(requireContext())
        viewModel = honkaiFactory.buildViewModel()
        setupObservers()

        viewModel.getCharacters()
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

                honkaiAdapter.setEvent { characterId ->
                    navigateToDetail(characterId)
                }
                adapter = honkaiAdapter
            }
        }
    }

    private fun setupObservers() {
        val observer = Observer<HonkaiViewModel.UiState> {
            if (it.isLoading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
                honkaiAdapter.submitList(it.character)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }


    private fun navigateToDetail(characterId: String) {

        findNavController().navigate(HonkaiFragmentDirections.actionHonkaiFragmentToHonkaiDetailFragment(characterId))
    }
}