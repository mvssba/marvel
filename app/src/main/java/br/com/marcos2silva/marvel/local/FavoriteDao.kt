package br.com.marcos2silva.marvel.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.marcos2silva.marvel.datasource.local.FavoriteLocalDataSource
import br.com.marcos2silva.marvel.local.model.CharacterFavorite

@Dao
interface FavoriteDao : FavoriteLocalDataSource {

    @Query("SELECT *FROM favorite order by name")
    override suspend fun favorites(): List<CharacterFavorite>

    @Insert
    override suspend fun insert(characterFavorite: CharacterFavorite)

    @Delete
    override suspend fun delete(characterFavorite: CharacterFavorite)

    @Query("SELECT *FROM favorite WHERE id = :id")
    override suspend fun favorite(id: Int): CharacterFavorite?
}