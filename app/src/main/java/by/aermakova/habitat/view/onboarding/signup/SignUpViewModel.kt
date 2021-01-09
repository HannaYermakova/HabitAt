package by.aermakova.habitat.view.onboarding.signup

import by.aermakova.habitat.model.useCase.SaveUserNameUseCase
import by.aermakova.habitat.view.base.BaseViewModel
import by.aermakova.habitat.view.navigation.EnterNavigation
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val navigation: EnterNavigation,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : BaseViewModel() {

    private val _tempUserName = BehaviorSubject.create<String>()
    val tempUserName: Observer<String>
        get() = _tempUserName

    val popBack = { navigation.popBack() }

    val saveUserName = {
        _tempUserName.value?.let {
            saveUserNameUseCase.saveValidUserName(
                it,
                disposable
            ) { navigation.enterMainScreen() }
        }
    }
}