package br.com.marcos2silva.marvel.characters.presentation

import br.com.marcos2silva.marvel.model.Character

sealed class DetailViewState {
    data class Success(val item: Character) : DetailViewState()
    data class Error(val message: String) : DetailViewState()
}
