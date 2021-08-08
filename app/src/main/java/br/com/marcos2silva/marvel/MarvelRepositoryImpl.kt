package br.com.marcos2silva.marvel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.marcos2silva.marvel.characters.datasource.CharactersPagingSource
import br.com.marcos2silva.marvel.data.MarvelRemoteDataSource
import br.com.marcos2silva.marvel.data.api.MarvelService_
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MarvelRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dataSource: MarvelRemoteDataSource,
//    private val dataSource: CharactersPagingSource,
    private val service_: MarvelService_
) : MarvelRepository {

    override suspend fun allCharacters(name: String): Flow<PagingData<CharacterResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CharactersPagingSource(service_, name) }
        ).flow
            .flowOn(ioDispatcher)
    }

    override suspend fun character(id: Int): CharacterResponse? {
        return dataSource.character(id).data.results.firstOrNull()
    }
}