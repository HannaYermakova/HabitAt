package by.aermakova.habitat.view.main.dashboard

import android.app.Activity
import by.aermakova.habitat.model.Preferences
import by.aermakova.habitat.model.di.module.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class DashboardModule {

    @Provides
    fun provideUserName(activity: Activity): String {
        return Preferences.getUserName(activity) ?: ""
    }

    @Provides
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    fun provideViewModel(viewModel: DashboardViewModel) = viewModel
}