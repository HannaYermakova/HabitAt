package by.aermakova.habitat.view.main.category.detail

import android.os.Bundle
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentCategoryItemBinding
import by.aermakova.habitat.view.base.BaseFragment

class CategoryItemFragment : BaseFragment<FragmentCategoryItemBinding, CategoryItemViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_category_item

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeUseCase(viewModel.habitObserver)
    }
}