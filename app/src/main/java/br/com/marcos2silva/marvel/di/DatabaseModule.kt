package br.com.marcos2silva.marvel.di

import androidx.room.Room
import br.com.marcos2silva.marvel.data.local.FavoriteDataBase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private const val DB_NAME = "marvel_db"

object DatabaseModule {
    private val database = module {
        single {
            Room.databaseBuilder(get(), FavoriteDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }


    fun load() {
        loadKoinModules(database)
    }
}