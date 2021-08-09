package br.com.marcos2silva.marvel.datasource

import br.com.marcos2silva.marvel.data.response.MarvelResponse
import br.com.marcos2silva.marvel.network.Result

interface MarvelRemoteDataSource {
    suspend fun character(id: Int): MarvelResponse
}