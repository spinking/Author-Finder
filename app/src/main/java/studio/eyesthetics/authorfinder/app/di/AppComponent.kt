package studio.eyesthetics.authorfinder.app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import studio.eyesthetics.authorfinder.MainActivity
import studio.eyesthetics.authorfinder.app.App
import studio.eyesthetics.authorfinder.app.di.modules.AppModule
import studio.eyesthetics.authorfinder.app.di.modules.NetworkModule
import studio.eyesthetics.authorfinder.app.di.modules.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    interface ComponentProvider {
        val appComponent: AppComponent
    }

    //activity, fragment
    fun inject(activity: MainActivity)
}