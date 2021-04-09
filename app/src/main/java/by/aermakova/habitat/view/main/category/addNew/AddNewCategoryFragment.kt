package by.aermakova.habitat.view.main.category.addNew

import android.os.Bundle
import androidx.lifecycle.Observer
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentAddNewCategoryBinding
import by.aermakova.habitat.view.base.BaseFragment


class AddNewCategoryFragment :
    BaseFragment<FragmentAddNewCategoryBinding, AddNewCategoryViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_add_new_category

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.back.setOnClickListener { backNavigation() }
        subscribeToNavigationChanged()
        setColorsObserver()
    }

    private fun setColorsObserver() {
        observe(viewModel.selectColorUseCase.allColors) {
            viewModel.selectColorUseCase.setColors(it)
        }
        viewModel.loadColors()
    }

    private fun subscribeToNavigationChanged() {
        viewModel.saveCategoryCommand.observe(viewLifecycleOwner, Observer { backNavigation() })
        viewModel.showErrorMessageCommand.observe(
            viewLifecycleOwner,
            Observer { message: Int? -> showSnackBarMessage(message!!) })
    }
}