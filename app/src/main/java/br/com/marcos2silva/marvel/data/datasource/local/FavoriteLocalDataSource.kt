package br.com.marcos2silva.marvel.data.datasource.local

import br.com.marcos2silva.marvel.data.local.model.CharacterFavorite

interface FavoriteLocalDataSource {
    suspend fun favorites(): List<CharacterFavorite>
    suspend fun favorite(id: Int): CharacterFavorite?
    suspend fun insert(characterFavorite: CharacterFavorite)
    suspend fun delete(characterFavorite: CharacterFavorite)
}