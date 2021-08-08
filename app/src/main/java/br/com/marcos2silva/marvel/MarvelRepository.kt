package br.com.marcos2silva.marvel

import androidx.paging.PagingData
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun allCharacters(name: String): Flow<PagingData<CharacterResponse>>
    suspend fun character(id: Int): CharacterResponse?
}
