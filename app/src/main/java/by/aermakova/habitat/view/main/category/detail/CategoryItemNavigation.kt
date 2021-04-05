package by.aermakova.habitat.view.main.category.detail

import androidx.navigation.NavController

class CategoryItemNavigation(private val controller: NavController) {

    fun navigateToAddNewElementFragment(categoryId: Long) {
        controller.navigate(
            CategoryItemFragmentDirections.actionCategoryItemFragmentToAddNewHabitFragment(
                categoryId
            )
        )
    }

    fun popBack() {
        controller.popBackStack()
    }
}