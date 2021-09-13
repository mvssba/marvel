package br.com.marcos2silva.marvel.data.response

data class CharacterResponse(
    val id: Int,
    val thumbnail: ThumbnailResponse,
    val name: String,
    val description: String
)
