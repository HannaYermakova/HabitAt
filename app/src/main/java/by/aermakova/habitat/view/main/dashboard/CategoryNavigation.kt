package by.aermakova.habitat.view.main.dashboard

import android.util.Log
import androidx.navigation.NavController
import by.aermakova.habitat.R
import by.aermakova.habitat.view.navigation.MainFlowNavigation

class CategoryNavigation(private val controller: NavController) : MainFlowNavigation {

    override fun navigateToAddNewElementFragment() {
        controller.navigate(R.id.action_mainFlowFragment_to_addNewCategoryFragment)
    }

    override fun navigateToShowDetailsFragment(id: Long) {
        Log.d("A_DashboardNavigation", "navigateToShowDetailsFragment")
    }

    override fun popBack() {
        controller.popBackStack()
    }
}