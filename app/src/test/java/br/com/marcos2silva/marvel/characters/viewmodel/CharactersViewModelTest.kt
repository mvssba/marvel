package br.com.marcos2silva.marvel.characters.viewmodel

import br.com.marcos2silva.marvel.MarvelRepository
import br.com.marcos2silva.marvel.characters.presentation.CharactersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runBlockingTest
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

    private val repository: MarvelRepository = mock()

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setup() {
        charactersViewModel = CharactersViewModel(repository)
    }

    @Test
    fun `getCharacters should call allCharacters return all characters`() = runBlockingTest {

        val name = ""
        whenever(repository.allCharacters(name)).thenReturn(emptyFlow())

        charactersViewModel.getCharacters(name)


    }
}