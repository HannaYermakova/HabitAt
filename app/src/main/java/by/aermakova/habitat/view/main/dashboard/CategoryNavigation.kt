package by.aermakova.habitat.view.main.dashboard

import androidx.navigation.NavController
import by.aermakova.habitat.view.main.flow.MainFlowFragmentDirections
import by.aermakova.habitat.view.navigation.MainFlowNavigation

class CategoryNavigation(private val controller: NavController) : MainFlowNavigation {

    override fun navigateToAddNewElementFragment() {
        controller.navigate(MainFlowFragmentDirections.actionMainFlowFragmentToAddNewCategoryFragment())
    }

    override fun navigateToShowDetailsFragment(id: Long) {
        controller.navigate(MainFlowFragmentDirections.actionMainFlowFragmentToCategoryItemFragment(id))
    }

    override fun popBack() {
        controller.popBackStack()
    }
}