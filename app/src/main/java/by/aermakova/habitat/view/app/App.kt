package by.aermakova.habitat.view.app

import android.app.Application
import by.aermakova.habitat.model.di.component.ApplicationComponent
import by.aermakova.habitat.model.di.component.DaggerApplicationComponent
import by.aermakova.habitat.model.di.module.ActivityModule
import by.aermakova.habitat.model.di.module.ApplicationModule

class App : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.activityComponent.inject(this)

    }

    fun tryInjectAppActivity(appActivity: AppActivity): Boolean {
        return applicationComponent
                .getActivityComponent(ActivityModule(appActivity))
                .activityInjector
                .maybeInject(appActivity)
    }
}