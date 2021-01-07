package by.aermakova.habitat.view.main.habit

import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class AddNewHabitModule {

    @Provides
    @IntoMap
    @ViewModelKey(AddNewHabitViewModel::class)
    fun provideViewModel(viewModel: AddNewHabitViewModel): ViewModel = viewModel

}