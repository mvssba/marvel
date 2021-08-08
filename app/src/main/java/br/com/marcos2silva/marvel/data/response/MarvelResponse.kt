package br.com.marcos2silva.marvel.data.response

data class MarvelResponse(
    val code: Int,
    val etag: String,
    val data: DataResponse
)
