package by.aermakova.habitat.view.main.dashboard

import android.util.Log
import androidx.navigation.NavController
import by.aermakova.habitat.R
import by.aermakova.habitat.view.navigation.MainFlowNavigation

class HabitNavigation(private val controller: NavController): MainFlowNavigation {

    override fun navigateToAddNewElementFragment() {
        controller.navigate(R.id.action_mainFlowFragment_to_addNewHabitFragment)
    }

    override fun navigateToShowDetailsFragment(id: Long) {
        Log.d("A_HabitNavigation", "navigateToShowDetailsFragment")
    }

    override fun popBack() {
       controller.popBackStack()
    }
}