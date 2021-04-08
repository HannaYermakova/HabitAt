package by.aermakova.habitat.view.main.dashboard

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.db.pref.Preferences
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.model.CategoryModel
import by.aermakova.habitat.model.model.toModel
import by.aermakova.habitat.model.useCase.ObserveUseCase
import by.aermakova.habitat.view.custom.dataadapter.CategoryAdapter
import by.aermakova.habitat.view.custom.dataadapter.HabitDataMultiAdapter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class DashboardModule {

    @Provides
    fun provideNavController(activity: Activity): NavController =
        Navigation.findNavController(activity, R.id.app_host_fragment)

    @Provides
    fun provideCategoryNavigation(controller: NavController): CategoryNavigation =
        CategoryNavigation(controller)

    @Provides
    fun provideHabitNavigation(controller: NavController): HabitNavigation =
        HabitNavigation(controller)

    @Provides
    fun provideHabitObserve(router: HabitNavigation, dataBase: AppDataBase): ObserveUseCase<Habit> =
        object : ObserveUseCase<Habit>(HabitDataMultiAdapter(router)) {
            override fun getList(): LiveData<List<Habit>> {
                return dataBase.habitDao().getAllHabits()
            }
        }

    @Provides
    fun provideCategoryObserve(
        routing: CategoryNavigation,
        dataBase: AppDataBase
    ): ObserveUseCase<CategoryModel> =
        object : ObserveUseCase<CategoryModel>(CategoryAdapter(routing)) {
            override fun getList(): LiveData<List<CategoryModel>> {
                return map(dataBase.categoryDao().getAllCategory()){ it -> it.map { it.toModel() }}
            }
        }

    @Provides
    fun provideUserName(activity: Activity): String {
        return Preferences.getUserName(activity) ?: ""
    }

    @Provides
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    fun provideViewModel(viewModel: DashboardViewModel) = viewModel
}