package br.com.marcos2silva.marvel.favorites

import br.com.marcos2silva.marvel.model.Character

sealed class FavoritesViewState {
    data class Success(val favorites: List<Character>) : FavoritesViewState()
    object Empty : FavoritesViewState()
}