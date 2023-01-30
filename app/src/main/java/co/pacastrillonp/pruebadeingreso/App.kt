package co.pacastrillonp.pruebadeingreso

import android.app.Application
import co.pacastrillonp.pruebadeingreso.di.applicationModule
import co.pacastrillonp.pruebadeingreso.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(applicationModule)
        }
    }
}