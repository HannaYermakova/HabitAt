package by.aermakova.habitat.view.onboarding.signup

import androidx.lifecycle.ViewModel
import by.aermakova.habitat.model.di.module.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SignUpModule {

    @Provides
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun provideViewModel(viewModel: SignUpViewModel): ViewModel = viewModel
}