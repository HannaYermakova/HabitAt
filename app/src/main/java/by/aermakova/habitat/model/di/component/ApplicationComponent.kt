package by.aermakova.habitat.model.di.component


import by.aermakova.habitat.model.di.module.ActivityModule
import by.aermakova.habitat.model.di.module.ApplicationModule
import by.aermakova.habitat.model.di.module.FragmentModule
import by.aermakova.habitat.model.di.scope.ActivityScope
import by.aermakova.habitat.model.di.scope.ApplicationScope
import dagger.Component
import dagger.Subcomponent

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    val activityComponent: ActivityComponent

    fun getActivityComponent(activityModule: ActivityModule) : LocalActivityComponent

    @ActivityScope
    @Subcomponent(modules = [ActivityModule::class, FragmentModule::class])
    interface LocalActivityComponent : ActivityComponent
}