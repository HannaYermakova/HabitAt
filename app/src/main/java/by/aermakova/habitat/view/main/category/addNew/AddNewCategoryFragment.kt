package by.aermakova.habitat.view.main.category.addNew

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentAddNewCategoryBinding
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.dataadapter.CardColorAdapter
import by.aermakova.habitat.view.custom.layoutmanager.SpanningLinearLayoutManager
import by.aermakova.habitat.view.observer.ColorObserver

class AddNewCategoryFragment :
    BaseFragment<FragmentAddNewCategoryBinding, AddNewCategoryViewModel>(), ColorObserver {

    private lateinit var colorAdapter: CardColorAdapter

    override val layoutId: Int
        get() = R.layout.fragment_add_new_category

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.colorsRecycler.layoutManager = SpanningLinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        colorAdapter = CardColorAdapter()
        colorAdapter.registerObserver(this)
        binding.colorsRecycler.adapter = colorAdapter
        binding.back.setOnClickListener { backNavigation() }
        subscribeToNavigationChanged()
    }

    private fun subscribeToNavigationChanged() {
        viewModel.saveCategoryCommand.observe(viewLifecycleOwner, Observer { backNavigation() })
        viewModel.showErrorMessageCommand.observe(
            viewLifecycleOwner,
            Observer { message: Int? -> showSnackBarMessage(message!!) })
    }

    override fun update(color: Int) {
        viewModel.setSelectedColor(color)
    }

    override fun onDestroy() {
        colorAdapter.unregisterObserver(this)
        super.onDestroy()
    }
}