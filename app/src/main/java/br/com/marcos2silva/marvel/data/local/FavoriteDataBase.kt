package br.com.marcos2silva.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.marcos2silva.marvel.data.local.model.CharacterFavorite

@Database(
    entities = [CharacterFavorite::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}