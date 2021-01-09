package by.aermakova.habitat.view.onboarding.signup

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.pref.PreferencesManager
import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.SaveUserNameUseCase
import by.aermakova.habitat.view.navigation.EnterNavigation
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SignUpModule {

    @Provides
    fun providePreferencesManager(activity: Activity) = PreferencesManager(activity)

    @Provides
    fun provideSaveUserNameUseCase(preferencesManager: PreferencesManager) =
        SaveUserNameUseCase(preferencesManager)

    @Provides
    fun provideNavController(activity: Activity): NavController =
        Navigation.findNavController(activity, R.id.app_host_fragment)

    @Provides
    fun provideSignUpNavigation(controller: NavController): EnterNavigation =
        SignUpNavigation(controller)

    @Provides
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun provideViewModel(viewModel: SignUpViewModel): ViewModel = viewModel
}