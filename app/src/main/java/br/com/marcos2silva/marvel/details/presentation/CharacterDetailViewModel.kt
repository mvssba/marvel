package br.com.marcos2silva.marvel.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marcos2silva.marvel.MarvelRepository
import br.com.marcos2silva.marvel.characters.presentation.ViewState
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _uiState: MutableLiveData<ViewState> = MutableLiveData()
    val uiState: LiveData<ViewState> = _uiState

    fun character(id: Int) {
        viewModelScope.launch {
            val character = repository.character(id)

            character?.let {
                _uiState.value = ViewState.Character(character)
            } ?: run {

            }
        }
    }
}