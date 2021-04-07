package by.aermakova.habitat.view.main.habit

import android.os.Bundle
import androidx.lifecycle.Observer
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentAddNewHabitBinding
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.useCase.AlarmManagerLogic
import by.aermakova.habitat.view.base.BaseFragment


class AddNewHabitFragment :
    BaseFragment<FragmentAddNewHabitBinding, AddNewHabitViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_add_new_habit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setCategoriesObserver()
        subscribeToNavigationChanged(viewModel)
    }

    private fun setCategoriesObserver() {
        observe(viewModel.selectCategoryUseCase.allCategories) {
            viewModel.selectCategoryUseCase.setCategories(it)
        }
    }

    private fun subscribeToNavigationChanged(viewModel: AddNewHabitViewModel) {
        viewModel.showErrorMessageCommand.observe(
            viewLifecycleOwner,
            Observer { message: Int? -> showSnackBarMessage(message!!) })
        viewModel.saveHabitCommand.observe(viewLifecycleOwner, Observer { backNavigation() })
        viewModel.setNotificationLogicCommand.observe(
            viewLifecycleOwner,
            Observer { habit: Habit? -> createNotificationLogic(habit) })
    }

    private fun createNotificationLogic(habit: Habit?) {
        if (binding.notificationToggle.isChecked) {
            AlarmManagerLogic(requireContext().applicationContext, habit)
        }
    }
}