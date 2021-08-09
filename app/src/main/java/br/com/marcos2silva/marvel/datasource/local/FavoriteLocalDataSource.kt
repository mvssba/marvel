package br.com.marcos2silva.marvel.datasource.local

import br.com.marcos2silva.marvel.local.model.CharacterFavorite

interface FavoriteLocalDataSource {
    suspend fun favorites(): List<CharacterFavorite>
    suspend fun favorite(id: Int): CharacterFavorite?
    suspend fun insert(characterFavorite: CharacterFavorite)
    suspend fun delete(characterFavorite: CharacterFavorite)
}