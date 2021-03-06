package br.com.marcos2silva.marvel.model

data class Character(
    val id: Int,
    val name: String,
    val description: String = "",
    val thumbnail: String,
    var isFavorite: Boolean = false
)