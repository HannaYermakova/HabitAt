package by.aermakova.habitat.view.main.habit

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.SelectWeekdaysUseCase
import by.aermakova.habitat.view.custom.dialog.TimePickerNavigation
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class AddNewHabitModule {

    @Provides
    fun provideSelectWeekdaysUseCase() = SelectWeekdaysUseCase()

    @Provides
    fun provideNavController(activity: Activity): NavController =
        Navigation.findNavController(activity, R.id.app_host_fragment)

    @Provides
    fun provideTimePickerNavigation(controller: NavController): TimePickerNavigation =
        TimePickerNavigation(controller)

    @Provides
    @IntoMap
    @ViewModelKey(AddNewHabitViewModel::class)
    fun provideViewModel(viewModel: AddNewHabitViewModel): ViewModel = viewModel

}