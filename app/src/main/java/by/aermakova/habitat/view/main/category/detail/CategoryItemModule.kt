package by.aermakova.habitat.view.main.category.detail

import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.di.module.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CategoryItemModule {

    @Provides
    @IntoMap
    @ViewModelKey(CategoryItemViewModel::class)
    fun provideViewModel(viewModel: CategoryItemViewModel): ViewModel = viewModel
}