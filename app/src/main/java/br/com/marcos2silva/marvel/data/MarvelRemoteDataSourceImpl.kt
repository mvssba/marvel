package br.com.marcos2silva.marvel.data

import br.com.marcos2silva.marvel.data.api.MarvelService_
import br.com.marcos2silva.marvel.data.response.MarvelResponse

class MarvelRemoteDataSourceImpl(
    private val service: MarvelService_
) : MarvelRemoteDataSource {

    override suspend fun character(id: Int): MarvelResponse {
        return service.character(id)
    }
}