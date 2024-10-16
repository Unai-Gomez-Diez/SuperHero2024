package edu.unaigomdie.superhero2024.feature.honkai.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.unaigomdie.superhero2024.app.extension.loadImage
import edu.unaigomdie.superhero2024.databinding.FragmentHonkaiBottomSheetBinding
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai

class HonkaiDetailFragment: BottomSheetDialogFragment() {
    private var _binding: FragmentHonkaiBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var honkaiFactory: HonkaiFactory
    private lateinit var viewModel: HonkaiDetailViewModel

    val args: HonkaiDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHonkaiBottomSheetBinding.inflate(inflater, container, false)


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

    private fun setupView(honkai: Honkai) {
        binding.apply {
            image.loadImage(honkai.img)
            bottomSheet.apply {
                name.text = honkai.name
                path.text = honkai.path
                stars.text = honkai.rarity.toString()
            }
        }
    }

    private fun setupObservers(){
        val observer = Observer<HonkaiDetailViewModel.UiState> {
            if (it.isLoading) {
                //skeleton.showSkeleton()
            } else {
                //skeleton.showOriginal()
                setupView(it.honkai!!)
            }

        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }


}