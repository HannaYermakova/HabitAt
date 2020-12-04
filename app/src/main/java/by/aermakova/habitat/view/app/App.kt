package by.aermakova.habitat.view.app

import android.app.Application
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.di.DataBaseModule
import by.aermakova.habitat.model.di.DataBaseModule.ContextModule
import dagger.Component
import javax.inject.Singleton

class App : Application() {

    @Singleton
    @Component(modules = [DataBaseModule::class, ContextModule::class])
    interface AppComponent {
        val dataBase: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApp_AppComponent.builder().contextModule(ContextModule(applicationContext)).build()
    }

    companion object {
        var component: AppComponent? = null
            private set
    }
}