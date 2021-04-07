package by.aermakova.habitat.view.main.habit

import androidx.navigation.NavController

class AddNewHabitNavigation(private val controller: NavController)  {

     fun navigateToAddNewCategoryFragment() {
        controller.navigate(AddNewHabitFragmentDirections.actionAddNewHabitFragmentToAddNewCategoryFragment())
    }

     fun popBack() {
        controller.popBackStack()
    }
}