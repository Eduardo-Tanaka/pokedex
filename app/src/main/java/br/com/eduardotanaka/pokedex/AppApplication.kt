package br.com.eduardotanaka.pokedex

import br.com.eduardotanaka.pokedex.di.DaggerAppComponent
import dagger.android.DaggerApplication
import timber.log.Timber

class AppApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)
    override fun applicationInjector() = appComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}