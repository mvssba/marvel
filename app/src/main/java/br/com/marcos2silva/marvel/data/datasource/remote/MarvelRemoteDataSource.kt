package br.com.marcos2silva.marvel.data.datasource.remote

import br.com.marcos2silva.marvel.data.response.MarvelResponse

interface MarvelRemoteDataSource {
    suspend fun character(id: Int): MarvelResponse
}