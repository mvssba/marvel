package br.com.marcos2silva.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import br.com.marcos2silva.marvel.model.Character
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun allCharacters(name: String): LiveData<PagingData<Character>>
    suspend fun allCharactersFavorites(): List<Character>
    suspend fun insertFavorite(favorite: Character)
    suspend fun removeFavorite(favorite: Character)
}
