package by.aermakova.habitat.view.main.category.detail

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.ObserveHabitUseCase
import by.aermakova.habitat.model.useCase.ObserveUseCase
import by.aermakova.habitat.view.custom.dataadapter.HabitDataMultiAdapter
import by.aermakova.habitat.view.custom.dataadapter.HabitListAdapter
import by.aermakova.habitat.view.main.dashboard.HabitNavigation
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CategoryItemModule {

    @Provides
    fun provideNavController(activity: Activity): NavController =
        Navigation.findNavController(activity, R.id.app_host_fragment)

    @Provides
    fun provideHabitNavigation(controller: NavController) =
        CategoryItemNavigation(controller)

    @Provides
    fun provideArgs(fragment: CategoryItemFragment): Long {
        return fragment.navArgs<CategoryItemFragmentArgs>().value.id
    }

    @Provides
    fun provideHabitObserve(dataBase: AppDataBase): ObserveHabitUseCase =
        ObserveHabitUseCase(HabitListAdapter(), dataBase)

    @Provides
    @IntoMap
    @ViewModelKey(CategoryItemViewModel::class)
    fun provideViewModel(viewModel: CategoryItemViewModel): ViewModel = viewModel
}