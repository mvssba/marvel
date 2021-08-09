package br.com.marcos2silva.marvel.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.marcos2silva.marvel.CharacterHelper
import br.com.marcos2silva.marvel.CoroutineTestRule
import br.com.marcos2silva.marvel.MarvelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.mockito.quality.Strictness

@ExperimentalCoroutinesApi
class CharactersFavoritesViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository: MarvelRepository = mock()

    private lateinit var viewModel: CharactersFavoritesViewModel

    @Before
    fun setup() {
        viewModel = CharactersFavoritesViewModel(repository)
    }

    @Test
    fun `getCharacters should call allCharacters return view state empty`() = runBlockingTest {
        whenever(repository.allCharactersFavorites()).thenReturn(emptyList())

        viewModel.getCharacters()

        Assert.assertEquals(FavoritesViewState.Empty, viewModel.state.value)
    }

    @Test
    fun `getCharacters should call allCharacters return view state success`() = runBlockingTest {
        whenever(repository.allCharactersFavorites()).thenReturn(
            listOf(CharacterHelper.character)
        )

        viewModel.getCharacters()

        Assert.assertEquals(
            FavoritesViewState.Success(listOf(CharacterHelper.character)),
            viewModel.state.value
        )
    }
}