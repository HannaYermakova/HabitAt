package by.aermakova.habitat.view.main.category.addNew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentAddNewCategoryBinding
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.dataadapter.CardColorAdapter
import by.aermakova.habitat.view.custom.layoutmanager.SpanningLinearLayoutManager
import by.aermakova.habitat.view.observer.ColorObserver

class AddNewCategoryFragment : BaseFragment<FragmentAddNewCategoryBinding, AddNewCategoryViewModel>(), ColorObserver {
    private lateinit var mBinding: FragmentAddNewCategoryBinding
    private lateinit var mViewModel: AddNewCategoryViewModel
    private lateinit var colorAdapter: CardColorAdapter

    override val layoutId: Int
        get() = R.layout.fragment_add_new_category

/*    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_category, container, false)
        mViewModel = AddNewCategoryViewModel()
        return mBinding.root
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = mViewModel
        mBinding.colorsRecycler.layoutManager = SpanningLinearLayoutManager(requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false)
        colorAdapter = CardColorAdapter()
        colorAdapter.registerObserver(this)
        mBinding.colorsRecycler.adapter = colorAdapter
        mBinding.back.setOnClickListener { backNavigation() }
        subscribeToNavigationChanged()
    }

    private fun subscribeToNavigationChanged() {
        mViewModel.saveCategoryCommand.observe(viewLifecycleOwner, Observer { backNavigation() })
        mViewModel.showErrorMessageCommand.observe(viewLifecycleOwner, Observer { message: Int? -> showSnackBarMessage(message!!) })
    }

    override fun update(color: Int) {
        mViewModel.setSelectedColor(color)
    }

    override fun onDestroy() {
        colorAdapter.unregisterObserver(this)
        super.onDestroy()
    }
}