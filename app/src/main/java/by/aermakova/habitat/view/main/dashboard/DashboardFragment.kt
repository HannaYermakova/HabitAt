package by.aermakova.habitat.view.main.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentDashboardBinding
import by.aermakova.habitat.model.Preferences
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.dataadapter.CategoryAdapter
import by.aermakova.habitat.view.custom.dataadapter.HabitDataMultiAdapter
import by.aermakova.habitat.view.custom.layoutmanager.ItemOffsetDecoration

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    private var mHabitDataAdapter: HabitDataMultiAdapter? = null
    private var mCategoryAdapter: CategoryAdapter? = null

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel.setUser(Preferences.instance?.getUserName(requireContext()))
//        binding.viewModel = viewModel
//        binding.noteMark.isEnabled = false
        Handler().postDelayed({ setHabitsRecycler() }, 100)
        Handler().postDelayed({ setCategoryRecycler() }, 100)
        binding.addNewCategoryButton.setOnClickListener { navigateFragment(R.id.action_mainFlowFragment_to_addNewCategoryFragment) }
    }

    private fun setHabitsRecycler() {
        binding.habitRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.habitRecycler.addItemDecoration(ItemOffsetDecoration(10))
        mHabitDataAdapter = HabitDataMultiAdapter(View.OnClickListener { navigateFragment(R.id.action_mainFlowFragment_to_addNewHabitFragment) })
        binding.habitRecycler.adapter = mHabitDataAdapter
        observeHabits()
    }

    private fun setCategoryRecycler() {
        binding.categoryRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        mCategoryAdapter = CategoryAdapter(requireActivity())
        binding.categoryRecycler.adapter = mCategoryAdapter
        observeCategories()
    }

    private fun observeHabits() {
        viewModel.habits?.observe(viewLifecycleOwner,
                Observer { habits: List<Habit?>? -> mHabitDataAdapter!!.setHabitList(habits) })
    }

    private fun observeCategories() {
        viewModel.categories?.observe(viewLifecycleOwner,
                Observer { categories: List<Category?>? -> mCategoryAdapter!!.setCategories(categories) })
    }
}