package by.aermakova.habitat.view.main.dashboard

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.model.Preferences
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.GetListOfHabitsUseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class DashboardModule {

    @Provides
    fun provideGetListOfHabitsUseCase(dataBase: AppDataBase) =
        GetListOfHabitsUseCase(dataBase.habitDao())

    @Provides
    fun provideNavController(activity: Activity): NavController =
        Navigation.findNavController(activity, R.id.app_host_fragment)

    @Provides
    fun provideCategoryNavigation(controller: NavController) =
        CategoryNavigation(controller)

    @Provides
    fun provideHabitNavigation(controller: NavController) =
        HabitNavigation(controller)

    @Provides
    fun provideUserName(activity: Activity): String {
        return Preferences.getUserName(activity) ?: ""
    }

    @Provides
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    fun provideViewModel(viewModel: DashboardViewModel) = viewModel
}