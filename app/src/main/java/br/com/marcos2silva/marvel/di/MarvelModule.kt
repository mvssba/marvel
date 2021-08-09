package br.com.marcos2silva.marvel.di

import androidx.paging.PagingSource
import br.com.marcos2silva.marvel.MarvelRepository
import br.com.marcos2silva.marvel.MarvelRepositoryImpl
import br.com.marcos2silva.marvel.characters.datasource.CharactersPagingSource
import br.com.marcos2silva.marvel.characters.presentation.CharactersViewModel
import br.com.marcos2silva.marvel.data.MarvelRemoteDataSourceImpl
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import br.com.marcos2silva.marvel.datasource.MarvelRemoteDataSource
import br.com.marcos2silva.marvel.datasource.local.FavoriteLocalDataSource
import br.com.marcos2silva.marvel.details.presentation.CharacterDetailViewModel
import br.com.marcos2silva.marvel.favorites.CharactersFavoritesViewModel
import br.com.marcos2silva.marvel.local.FavoriteDataBase
import br.com.marcos2silva.marvel.usecase.CharacterUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object MarvelModule {

    private val presentation = module {
        viewModel { CharactersViewModel(repository = get()) }
        viewModel { CharacterDetailViewModel(repository = get()) }
        viewModel { CharactersFavoritesViewModel(repository = get()) }
    }

    private val domain = module {
        factory { CharacterUseCase(repository = get()) }
        factory<MarvelRepository> {
            MarvelRepositoryImpl(
                service_ = get(),
                marvelRemoteDataSource = get(),
                favoriteLocalDataSource = get()
            )
        }
    }

    private val data = module {
        factory<PagingSource<Int, CharacterResponse>> { CharactersPagingSource(service = get(), "") }
        factory<MarvelRemoteDataSource> { MarvelRemoteDataSourceImpl(service = get()) }
        factory<FavoriteLocalDataSource> { get<FavoriteDataBase>().favoriteDao() }
    }

    fun load() {
        loadKoinModules(
            listOf(
                presentation,
                domain,
                data
            )
        )
    }
}