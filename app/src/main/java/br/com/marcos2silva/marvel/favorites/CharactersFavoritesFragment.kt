package br.com.marcos2silva.marvel.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.marcos2silva.marvel.databinding.FragmentCharactersFavoritesBinding
import br.com.marcos2silva.marvel.favorites.adapter.CharacterFavoriteAdapter
import br.com.marcos2silva.marvel.model.Character
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFavoritesFragment : Fragment() {

    private val adapterFavorite = CharacterFavoriteAdapter()
    private val viewModel: CharactersFavoritesViewModel by viewModel()
    private lateinit var binding: FragmentCharactersFavoritesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharactersFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observableState()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getCharacters()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapterFavorite
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun observableState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FavoritesViewState.Success -> showFavorites(state.favorites)
                is FavoritesViewState.Empty -> showEmptyState(true)
            }
        }
    }

    private fun showEmptyState(isVisible: Boolean) {
        binding.layoutEmpty.emptyState.isVisible = isVisible
        binding.recyclerView.isVisible = !isVisible
    }

    private fun showFavorites(favorites: List<Character>) {
        showEmptyState(false)
        adapterFavorite.submitList(favorites)
    }
}