package by.aermakova.habitat.view.navigation

interface MainFlowNavigation {

    fun navigateToAddNewElementFragment()

    fun navigateToShowDetailsFragment(id: Long= -1L)

    fun popBack()
}