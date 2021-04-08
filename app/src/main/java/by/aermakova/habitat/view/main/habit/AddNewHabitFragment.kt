package by.aermakova.habitat.view.main.habit

import android.os.Bundle
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentAddNewHabitBinding
import by.aermakova.habitat.view.base.BaseFragment


class AddNewHabitFragment :
    BaseFragment<FragmentAddNewHabitBinding, AddNewHabitViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_add_new_habit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setCategoriesObserver()
        setHabitSaverListener()
    }

    private fun setCategoriesObserver() {
        observe(viewModel.selectCategoryUseCase.allCategories) {
            viewModel.selectCategoryUseCase.setCategories(it)
        }
        viewModel.loadCategories()
    }

    private fun setHabitSaverListener() {
        observe(viewModel.saveNewHabitUseCase.saveHabitCommand) { habit ->
            viewModel.setAlarmAndClose(habit)
        }
    }
}