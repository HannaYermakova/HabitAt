package by.aermakova.habitat.view.main.habit

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.HabitAlarmUseCase
import by.aermakova.habitat.model.useCase.SaveNewHabitUseCase
import by.aermakova.habitat.model.useCase.SelectCategoryUseCase
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
    fun provideHabitAlarmUseCase(activity: Activity): HabitAlarmUseCase =
        HabitAlarmUseCase(activity.applicationContext)

    @Provides
    fun provideNavController(activity: Activity): NavController =
        Navigation.findNavController(activity, R.id.app_host_fragment)

    @Provides
    fun provideTimePickerNavigation(controller: NavController): TimePickerNavigation =
        TimePickerNavigation(controller)

    @Provides
    fun provideAddNewHabitNavigation(controller: NavController): AddNewHabitNavigation =
        AddNewHabitNavigation(controller)

    @Provides
    fun provideSelectCategoryUseCase(
        appDataBase: AppDataBase,
        router: AddNewHabitNavigation
    ) = SelectCategoryUseCase(appDataBase) { router.navigateToAddNewCategoryFragment() }

/*    @Provides
    fun provideArgsId(fragment: AddNewHabitFragment): Long {
        return fragment.navArgs<AddNewHabitFragmentArgs>().value.id
    }*/

    @Provides
    fun provideSaveNewHabitUseCase(appDataBase: AppDataBase) = SaveNewHabitUseCase(appDataBase)

    @Provides
    @IntoMap
    @ViewModelKey(AddNewHabitViewModel::class)
    fun provideViewModel(viewModel: AddNewHabitViewModel): ViewModel = viewModel

}