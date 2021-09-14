package br.com.marcos2silva.marvel.data.repository

import br.com.marcos2silva.marvel.CharacterHelper
import br.com.marcos2silva.marvel.data.api.MarvelService
import br.com.marcos2silva.marvel.data.datasource.local.FavoriteLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MarvelRepositoryTest {

    private val favoriteLocalDataSource: FavoriteLocalDataSource = mock()
    private val service: MarvelService = mock()
    private lateinit var repository: MarvelRepository

    @Before
    fun setup() {
        repository = MarvelRepositoryImpl(
            Dispatchers.Main,
            favoriteLocalDataSource,
            service
        )
    }

    @Test
    fun `favorites should call local data favorites return list favorites character`() =
        runBlocking {
            whenever(favoriteLocalDataSource.favorites()).thenReturn(listOf(CharacterHelper.characterFavorite))

            val favorites = favoriteLocalDataSource.favorites()

            Assert.assertEquals(favorites, listOf(CharacterHelper.characterFavorite))
        }
}