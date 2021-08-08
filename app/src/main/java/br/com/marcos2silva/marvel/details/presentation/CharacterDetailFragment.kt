package br.com.marcos2silva.marvel.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.marcos2silva.marvel.characters.presentation.ViewState
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import br.com.marcos2silva.marvel.databinding.FragmentCharacterDetailBinding
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

        viewModel.character(1011334)

        observableState()
    }

    private fun observableState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Character -> fill(it.item)
            }
        }
    }

    private fun fill(item: CharacterResponse?) {
        item?.let { character ->
            binding.imageView.load("${character.thumbnail.path}.${character.thumbnail.extension}")
            binding.textViewTitle.text = character.name
            binding.textViewDescription.text = character.description
        }
    }
}