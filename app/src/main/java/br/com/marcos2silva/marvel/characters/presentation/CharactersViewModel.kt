package br.com.marcos2silva.marvel.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import br.com.marcos2silva.marvel.data.repository.MarvelRepository
import br.com.marcos2silva.marvel.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    var characterSelected: Character? = null

    suspend fun getCharacters(name: String): LiveData<PagingData<Character>> {
        return repository.allCharacters(name)
    }

    fun favorite(item: Character) {
        viewModelScope.launch {
            repository.insertFavorite(item)
        }
    }

    fun remove(item: Character) {
        viewModelScope.launch {
            repository.removeFavorite(item)
        }
    }
}