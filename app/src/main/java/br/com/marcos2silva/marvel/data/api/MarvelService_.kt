package br.com.marcos2silva.marvel.data.api

import br.com.marcos2silva.marvel.data.response.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService_ {

    @GET("v1/public/characters")
    suspend fun allCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MarvelResponse

    @GET("v1/public/characters")
    suspend fun charactersByName(
        @Query("name") name: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MarvelResponse

    @GET("v1/public/characters/{charactersId}")
    suspend fun character(@Path("charactersId") id: Int): MarvelResponse
}