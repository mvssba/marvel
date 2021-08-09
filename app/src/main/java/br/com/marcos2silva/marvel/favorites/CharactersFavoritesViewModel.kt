package br.com.marcos2silva.marvel.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marcos2silva.marvel.data.repository.MarvelRepository
import kotlinx.coroutines.launch

class CharactersFavoritesViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _state: MutableLiveData<FavoritesViewState> = MutableLiveData()
    val state: LiveData<FavoritesViewState> = _state

    fun getCharacters() {
        viewModelScope.launch {
            val favorites = repository.allCharactersFavorites()

            if (favorites.isEmpty()) {
                _state.postValue(FavoritesViewState.Empty)
            } else {
                _state.postValue(FavoritesViewState.Success(favorites))
            }
        }
    }
}