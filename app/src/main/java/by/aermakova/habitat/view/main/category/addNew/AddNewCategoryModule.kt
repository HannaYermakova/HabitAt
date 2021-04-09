package by.aermakova.habitat.view.main.category.addNew

import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.SelectColorUseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
class AddNewCategoryModule {

    @Provides
    fun provideSelectColorUseCase() = SelectColorUseCase()

    @Provides
    @IntoMap
    @ViewModelKey(AddNewCategoryViewModel::class)
    fun provideViewModel(viewModel: AddNewCategoryViewModel): ViewModel = viewModel
}