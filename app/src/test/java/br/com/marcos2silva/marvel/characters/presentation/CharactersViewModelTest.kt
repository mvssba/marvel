package br.com.marcos2silva.marvel.characters.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import br.com.marcos2silva.marvel.CharacterHelper
import br.com.marcos2silva.marvel.CoroutineTestRule
import br.com.marcos2silva.marvel.data.repository.MarvelRepository
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
class CharactersViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository: MarvelRepository = mock()

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setup() {
        charactersViewModel = CharactersViewModel(repository)
    }

    @Test
    fun `getCharacters should call allCharacters return all characters`() = runBlockingTest {

        val dataPaging = MutableLiveData(PagingData.from(listOf(CharacterHelper.character)))

        val name = ""
        whenever(repository.allCharacters(name)).thenReturn(dataPaging)

        val pagingDataCharacter = charactersViewModel.getCharacters(name)

        Assert.assertEquals(dataPaging, pagingDataCharacter)
    }
}