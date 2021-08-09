package br.com.marcos2silva.marvel.usecase

import androidx.paging.PagingData
import br.com.marcos2silva.marvel.MarvelRepository
import br.com.marcos2silva.marvel.model.Character
import kotlinx.coroutines.flow.Flow

class CharacterUseCase(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Character>> {
        return repository.allCharacters("")
    }
}
