package by.aermakova.habitat.view.main.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentDashboardBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.Preferences
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.dataadapter.CategoryAdapter
import by.aermakova.habitat.view.custom.dataadapter.HabitDataMultiAdapter
import by.aermakova.habitat.view.custom.layoutmanager.ItemOffsetDecoration

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    private lateinit var mBinding: FragmentDashboardBinding
    private lateinit var mView: View
    private lateinit var mViewModel: DashboardViewModel
    private var mHabitDataAdapter: HabitDataMultiAdapter? = null
    private var mCategoryAdapter: CategoryAdapter? = null

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

/*    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        mView = mBinding.root
        hideKeyboard()
        return mView
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        mViewModel.setUser(Preferences.instance?.getUserName(requireContext()))
        mBinding.viewModel = mViewModel
        mBinding.noteMark.isEnabled = false
        Handler().postDelayed({ setHabitsRecycler() }, 100)
        Handler().postDelayed({ setCategoryRecycler() }, 100)
        mBinding.addNewCategoryButton.setOnClickListener { navigateFragment(R.id.action_mainFlowFragment_to_addNewCategoryFragment) }
    }

    private fun setHabitsRecycler() {
        mBinding.habitRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.habitRecycler.addItemDecoration(ItemOffsetDecoration(10))
        mHabitDataAdapter = HabitDataMultiAdapter(View.OnClickListener { navigateFragment(R.id.action_mainFlowFragment_to_addNewHabitFragment) })
        mBinding.habitRecycler.adapter = mHabitDataAdapter
        observeHabits()
    }

    private fun setCategoryRecycler() {
        mBinding.categoryRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        mCategoryAdapter = CategoryAdapter(requireActivity())
        mBinding.categoryRecycler.adapter = mCategoryAdapter
        observeCategories()
    }

    private fun observeHabits() {
        mViewModel.habits?.observe(viewLifecycleOwner,
                Observer { habits: List<Habit?>? -> mHabitDataAdapter!!.setHabitList(habits) })
    }

    private fun observeCategories() {
        mViewModel.categories?.observe(viewLifecycleOwner,
                Observer { categories: List<Category?>? -> mCategoryAdapter!!.setCategories(categories) })
    }
}