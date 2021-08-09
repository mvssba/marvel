package br.com.marcos2silva.marvel.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.marcos2silva.marvel.characters.adapter.CharacterAdapter
import br.com.marcos2silva.marvel.databinding.FragmentCharactersBinding
import br.com.marcos2silva.marvel.viewpager.ViewPagerFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModel()

    private val characterAdapter by lazy {
        CharacterAdapter({ id ->
            val action = ViewPagerFragmentDirections.actionViewPagerFragmentToCharacterDetailFragment(id)
            findNavController().navigate(action)
        }, { item ->
            if (item.isFavorite) viewModel.remove(item)
            else viewModel.favorite(item)
        })
    }

    private lateinit var binding: FragmentCharactersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        observableState()

        observableFlow()
    }

    private fun observableFlow() {
        lifecycleScope.launch {
            val name = binding.editTextTextPersonName.text.toString()

            viewModel.getCharacters(name)
                .distinctUntilChanged()
                .collectLatest {
                    characterAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = characterAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupSearch() {
        binding.editTextTextPersonName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                observableFlow()
                true
            }

            false
        }
    }

    private fun observableState() {
        characterAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                showProgressBar(true)
            } else {
                showProgressBar(false)

                setError(loadState)
            }
        }

//        viewModel.state.observe(viewLifecycleOwner) {
//            characterAdapter.refresh()
//        }
    }

    private fun setError(loadState: CombinedLoadStates) {
        val errorState = when {
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }

        errorState?.let {
            Toast.makeText(activity, it.error.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgressBar(visible: Boolean) {
        binding.progressCircular.isVisible = visible
    }
}