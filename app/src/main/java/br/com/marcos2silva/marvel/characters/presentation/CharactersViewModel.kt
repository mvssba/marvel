package br.com.marcos2silva.marvel.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import br.com.marcos2silva.marvel.MarvelRepository
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    suspend fun getCharacters(name: String): Flow<PagingData<CharacterResponse>> {
        return repository.allCharacters(name)
    }
}