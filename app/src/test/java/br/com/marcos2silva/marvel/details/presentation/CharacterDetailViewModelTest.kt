package br.com.marcos2silva.marvel.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.marcos2silva.marvel.CharacterHelper
import br.com.marcos2silva.marvel.CoroutineTestRule
import br.com.marcos2silva.marvel.data.repository.MarvelRepository
import br.com.marcos2silva.marvel.characters.presentation.DetailViewState
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
class CharacterDetailViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository: MarvelRepository = mock()

    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setup() {
        viewModel = CharacterDetailViewModel(repository)
    }

    @Test
    fun `character should call repository character returning a character by id`() = runBlockingTest {
        val id = 1

        whenever(repository.character(id)).thenReturn(CharacterHelper.character)

        viewModel.character(id)

        Assert.assertEquals(
            DetailViewState.Success(CharacterHelper.character), viewModel.uiState.value
        )
    }
}