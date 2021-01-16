package studio.eyesthetics.authorfinder.app

import android.app.Application
import studio.eyesthetics.authorfinder.app.di.AppComponent
import studio.eyesthetics.authorfinder.app.di.DaggerAppComponent
import studio.eyesthetics.authorfinder.data.network.NetworkMonitor

class App : Application(), AppComponent.ComponentProvider {

    override lateinit var appComponent: AppComponent

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        NetworkMonitor.registerNetworkMonitor(applicationContext)

        appComponent.inject(this)
    }
}