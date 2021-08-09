package br.com.marcos2silva.marvel.characters.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.marcos2silva.marvel.data.api.MarvelService
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import br.com.marcos2silva.marvel.network.ErrorHandle
import com.google.gson.GsonBuilder
import retrofit2.HttpException

private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 0

class CharactersPagingSource(
    private val service: MarvelService,
    private val name: String
) : PagingSource<Int, CharacterResponse>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) {
            (position * NETWORK_PAGE_SIZE)
        } else INITIAL_LOAD_SIZE

        return try {
            val response =
                if (name.isEmpty())
                    service.allCharacters(offset = offset, limit = NETWORK_PAGE_SIZE)
                else
                    service.charactersByName(name = name, offset = offset, limit = NETWORK_PAGE_SIZE)

            LoadResult.Page(
                data = response.data.results,
                prevKey = null,
                nextKey = if (response.data.results.isEmpty()) null else position + 1
            )
        } catch (exception: HttpException) {
            LoadResult.Error(handleError(exception))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun handleError(exception: HttpException): Throwable {
        val body = exception.response()?.errorBody()
        val error = GsonBuilder().create().fromJson(body?.string(), ErrorHandle::class.java)

        return Throwable(message = error.message)
    }
}