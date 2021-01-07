package by.aermakova.habitat.model.di.component

import android.app.Activity
import by.aermakova.habitat.model.di.module.ActivityModule
import by.aermakova.habitat.view.app.App
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.DispatchingAndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class, ActivityModule::class])
interface ActivityComponent {

    val activityInjector: DispatchingAndroidInjector<Activity>

    fun inject(application: App)
}