package br.com.marcos2silva.marvel.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.marcos2silva.marvel.characters.presentation.DetailViewState
import br.com.marcos2silva.marvel.databinding.FragmentCharacterDetailBinding
import br.com.marcos2silva.marvel.model.Character
import coil.load
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {

    //    private val args: CharacterDetailFragment by navArgs()
    private val viewModel: CharacterDetailViewModel by viewModel()

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.get("id") as? Int
        id?.let { viewModel.character(it) }

        observableState()
    }

    private fun observableState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailViewState.Success -> fill(it.item)
            }
        }
    }

    private fun fill(item: Character) {
        binding.imageView.load(item.thumbnail)
        binding.textViewTitle.text = item.name
        binding.textViewDescription.text = item.description
    }
}