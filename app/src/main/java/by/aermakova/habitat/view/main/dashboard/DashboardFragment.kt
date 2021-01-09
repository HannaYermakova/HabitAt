package by.aermakova.habitat.view.main.dashboard

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentDashboardBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.dataadapter.CategoryAdapter

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    private var mCategoryAdapter: CategoryAdapter? = null

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Handler().postDelayed({ setCategoryRecycler() }, 100)
    }

    private fun setCategoryRecycler() {
        binding.categoryRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        mCategoryAdapter = CategoryAdapter(requireActivity())
        binding.categoryRecycler.adapter = mCategoryAdapter
        observeCategories()
    }

    private fun observeCategories() {
        viewModel.categories?.observe(viewLifecycleOwner,
                Observer { categories: List<Category?>? -> mCategoryAdapter!!.setCategories(categories) })
    }
}