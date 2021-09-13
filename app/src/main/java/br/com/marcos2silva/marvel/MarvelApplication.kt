package br.com.marcos2silva.marvel

import android.app.Application
import br.com.marcos2silva.marvel.di.DatabaseModule
import br.com.marcos2silva.marvel.di.MarvelModule
import br.com.marcos2silva.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)

            NetworkModule.load()
            MarvelModule.load()
            DatabaseModule.load()
        }
    }
}