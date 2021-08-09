package br.com.marcos2silva.marvel.data.datasource.remote

import br.com.marcos2silva.marvel.data.api.MarvelService
import br.com.marcos2silva.marvel.data.response.MarvelResponse
import br.com.marcos2silva.marvel.data.datasource.remote.MarvelRemoteDataSource

class MarvelRemoteDataSourceImpl(
    private val service: MarvelService
) : MarvelRemoteDataSource {

    override suspend fun character(id: Int): MarvelResponse {
        return service.character(id)
    }
}