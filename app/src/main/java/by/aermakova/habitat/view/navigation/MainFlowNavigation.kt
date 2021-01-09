package by.aermakova.habitat.view.navigation

interface MainFlowNavigation {

    fun navigateToAddNewElementFragment()

    fun navigateToShowDetailsFragment(id: Long)

    fun popBack()
}