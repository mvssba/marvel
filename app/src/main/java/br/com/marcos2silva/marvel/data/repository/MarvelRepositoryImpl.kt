package br.com.marcos2silva.marvel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.map
import br.com.marcos2silva.marvel.characters.datasource.CharactersPagingSource
import br.com.marcos2silva.marvel.data.api.MarvelService
import br.com.marcos2silva.marvel.data.datasource.local.FavoriteLocalDataSource
import br.com.marcos2silva.marvel.data.local.model.CharacterFavorite
import br.com.marcos2silva.marvel.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
    private val service: MarvelService
) : MarvelRepository {

    override suspend fun removeFavorite(favorite: Character) {
        favoriteLocalDataSource.delete(
            CharacterFavorite(favorite.id, favorite.name, favorite.thumbnail)
        )
    }

    override suspend fun insertFavorite(favorite: Character) {
        favoriteLocalDataSource.insert(
            CharacterFavorite(favorite.id, favorite.name, favorite.thumbnail)
        )
    }

    override suspend fun allCharactersFavorites(): List<Character> {
        return favoriteLocalDataSource.favorites().map {
            Character(
                id = it.id,
                name = it.name,
                thumbnail = it.thumbnail
            )
        }
    }

    override suspend fun allCharacters(name: String): LiveData<PagingData<Character>> {
        val localCharacters = favoriteLocalDataSource.favorites()

        return withContext(ioDispatcher) {
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = { CharactersPagingSource(service, name) }
            ).liveData
                .map { pagingData ->
                    pagingData.map { characterResponse ->
                        val favorite =
                            localCharacters.singleOrNull { it.id == characterResponse.id }

                        Character(
                            id = characterResponse.id,
                            name = characterResponse.name,
                            description = characterResponse.description,
                            thumbnail = "${characterResponse.thumbnail.path}.${characterResponse.thumbnail.extension}",
                            isFavorite = favorite?.let { true } ?: false
                        )
                    }
                }
        }
    }
}