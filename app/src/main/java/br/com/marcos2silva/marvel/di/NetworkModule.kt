package br.com.marcos2silva.marvel.di

import br.com.marcos2silva.marvel.data.api.MarvelService_
import br.com.marcos2silva.marvel.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private val network = module {
        factory { AuthInterceptor() }
        factory { providerOkHttpClient(get()) }
        factory { providerMarvelService(get()) }
        single { providerRetrofit(get()) }
    }

    private fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("http://gateway.marvel.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun providerOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(authInterceptor)
            .build()
    }

    private fun providerMarvelService(retrofit: Retrofit) = retrofit.create(MarvelService_::class.java)

    fun load() {
        loadKoinModules(network)
    }
}