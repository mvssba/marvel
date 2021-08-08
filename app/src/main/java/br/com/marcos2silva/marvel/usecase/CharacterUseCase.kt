package br.com.marcos2silva.marvel.usecase

import androidx.paging.PagingData
import br.com.marcos2silva.marvel.MarvelRepository
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

class CharacterUseCase(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke(): Flow<PagingData<CharacterResponse>> {
        return repository.allCharacters("")
    }
}
