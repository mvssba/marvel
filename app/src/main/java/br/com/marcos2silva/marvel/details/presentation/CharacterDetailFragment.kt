package br.com.marcos2silva.marvel.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.marcos2silva.marvel.characters.presentation.CharactersViewModel
import br.com.marcos2silva.marvel.databinding.FragmentCharacterDetailBinding
import coil.load
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {

    private val characterViewModel: CharactersViewModel by sharedViewModel()

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fill()
    }

    private fun fill() {
        val item = characterViewModel.characterSelected

        item?.let {
            binding.imageView.load(item.thumbnail)
            binding.textViewTitle.text = item.name
            binding.textViewDescription.text = item.description
        }
    }
}