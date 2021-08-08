package br.com.marcos2silva.marvel.characters.presentation

import br.com.marcos2silva.marvel.data.response.CharacterResponse

sealed class ViewState {
    data class Character(val item: CharacterResponse?) : ViewState()
    data class Error(val message: String) : ViewState()
}
