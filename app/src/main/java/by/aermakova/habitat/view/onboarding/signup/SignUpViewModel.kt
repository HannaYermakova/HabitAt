package by.aermakova.habitat.view.onboarding.signup

import androidx.lifecycle.MutableLiveData
import by.aermakova.habitat.model.useCase.SaveUserNameUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import by.aermakova.habitat.view.navigation.EnterNavigation
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val navigation: EnterNavigation,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : BaseViewModel() {

    val tempUserName = MutableLiveData<String>()

    val popBack = { navigation.popBack() }

    val saveUserName = {
        tempUserName.value?.let {
            saveUserNameUseCase.saveValidUserName(
                it,
                disposable
            ) { navigation.enterMainScreen() }
        }
    }
}