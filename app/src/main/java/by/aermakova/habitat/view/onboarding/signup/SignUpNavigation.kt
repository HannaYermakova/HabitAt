package by.aermakova.habitat.view.onboarding.signup

import androidx.navigation.NavController
import by.aermakova.habitat.R
import by.aermakova.habitat.view.navigation.EnterNavigation

class SignUpNavigation(private val controller: NavController) : EnterNavigation {

    override fun enterMainScreen() {
        controller.navigate(R.id.action_welcomeFlowFragment_to_mainFlowFragment)
    }

    override fun popBack() {
        controller.popBackStack()
    }
}