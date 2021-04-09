package by.aermakova.habitat.view.main.category.addNew

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.SaveNewCategoryUseCase
import by.aermakova.habitat.model.useCase.SelectColorUseCase
import by.aermakova.habitat.view.navigation.MainFlowNavigation
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
class AddNewCategoryModule {

    @Provides
    fun provideNavController(activity: Activity): NavController =
        Navigation.findNavController(activity, R.id.app_host_fragment)

    @Provides
    fun provideMainFlowNavigation(controller: NavController): MainFlowNavigation =
        object :MainFlowNavigation{
            override fun navigateToAddNewElementFragment() {

            }

            override fun navigateToShowDetailsFragment(id: Long) {

            }

            override fun popBack() {
                controller.popBackStack()
            }
        }

    @Provides
    fun provideSelectColorUseCase() = SelectColorUseCase()

    @Provides
    fun provideSaveNewCategoryUseCase(appDataBase: AppDataBase) =
        SaveNewCategoryUseCase(appDataBase)

    @Provides
    @IntoMap
    @ViewModelKey(AddNewCategoryViewModel::class)
    fun provideViewModel(viewModel: AddNewCategoryViewModel): ViewModel = viewModel
}