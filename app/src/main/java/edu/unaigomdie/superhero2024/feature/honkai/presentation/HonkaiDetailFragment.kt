package edu.unaigomdie.superhero2024.feature.honkai.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.app.extension.loadImage
import edu.unaigomdie.superhero2024.databinding.FragmentHonkaiDetailBinding
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character
import edu.unaigomdie.superhero2024.feature.superhero.presentation.SuperHeroDetailFragmentArgs

class HonkaiDetailFragment: BottomSheetDialogFragment() {
    private var _binding: FragmentHonkaiDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var honkaiFactory: HonkaiFactory
    private lateinit var viewModel: HonkaiDetailViewModel

    val args: HonkaiDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHonkaiDetailBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        honkaiFactory = HonkaiFactory(requireContext())
        viewModel = honkaiFactory.buildViewModelDetail()
        setupObservers()
        val characterId = args.characterId
        viewModel.getCharacter(characterId)
    }

    private fun setupView(character: Character) {
        binding.apply {
            image.loadImage(character.img)
            name.text = character.name
            path.text = character.path
            stars.text = character.rarity.toString()
        }
    }

    private fun setupObservers(){
        val observer = Observer<HonkaiDetailViewModel.UiState> {
            if (it.isLoading) {
                //skeleton.showSkeleton()
            } else {
                //skeleton.showOriginal()
                setupView(it.character!!)
            }

        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }


}