package by.aermakova.habitat.view.main.dashboard

import by.aermakova.habitat.model.di.module.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class DashboardModule {

    @Provides
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    fun provideViewModel(viewModel: DashboardViewModel) = viewModel
}