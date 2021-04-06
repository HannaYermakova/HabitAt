package by.aermakova.habitat.view.main.habit

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentAddNewHabitBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.useCase.AlarmManagerLogic
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.dataadapter.CategoryPillsAdapter
import by.aermakova.habitat.view.custom.dataadapter.WeekDayAdapter
import by.aermakova.habitat.view.custom.layoutmanager.SpanningLinearLayoutManager
import by.aermakova.habitat.view.observer.CategoryObserver
import by.aermakova.habitat.view.observer.WeekDayObserver
import java.util.*


class AddNewHabitFragment :
    BaseFragment<FragmentAddNewHabitBinding, AddNewHabitViewModel>(),
    WeekDayObserver,
    CategoryObserver {

    private lateinit var mCategoriesAdapter: CategoryPillsAdapter
    private lateinit var mWeekDayAdapter: WeekDayAdapter

    override val layoutId: Int
        get() = R.layout.fragment_add_new_habit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createCategoryRecycler()
        createWeekDaysRecycler()
        binding.back.setOnClickListener { backNavigation() }
        subscribeToNavigationChanged(viewModel)
    }

    private fun createCategoryRecycler() {
        binding.categoryRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        mCategoriesAdapter = CategoryPillsAdapter(View.OnClickListener { navigateFragment(R.id.action_addNewHabitFragment_to_addNewCategoryFragment) })
        mCategoriesAdapter.registerObserver(this)
        binding.categoryRecycler.adapter = mCategoriesAdapter
        setCategories()
    }

    private fun createWeekDaysRecycler() {
        binding.weekDaysRecycler.layoutManager =
            SpanningLinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        mWeekDayAdapter = WeekDayAdapter()
        mWeekDayAdapter.registerObserver(this)
        binding.weekDaysRecycler.adapter = mWeekDayAdapter
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

    private fun setCategories() {
        if (arguments != null) {
            val category: Category? = requireArguments().getParcelable(BUNDLE_TAG)
            if (category != null) {
                val categoryList: MutableList<Category> = ArrayList()
                categoryList.add(category)
                mCategoriesAdapter.setCategories(categoryList)
            }
        } else viewModel.allCategories.observe(
            viewLifecycleOwner,
            Observer { mCategoriesAdapter.setCategories(it) })
    }

    override fun updateCategory(categoryId: Long) {
        viewModel.setCategoryId(categoryId)
    }

    override fun updateWeekDays(days: BooleanArray?) {
        viewModel.setWeekDays(days!!)
    }

    override fun onDestroy() {
        mCategoriesAdapter.unregisterObserver(this)
        mWeekDayAdapter.unregisterObserver(this)
        super.onDestroy()
    }

    companion object {
        private const val BUNDLE_TAG = "category"
    }
}