package by.aermakova.habitat.view.main.habit

import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentAddNewHabitBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.useCase.AlarmManagerLogic
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.TimePickerFragment
import by.aermakova.habitat.view.custom.dataadapter.CategoryPillsAdapter
import by.aermakova.habitat.view.custom.dataadapter.WeekDayAdapter
import by.aermakova.habitat.view.custom.layoutmanager.SpanningLinearLayoutManager
import by.aermakova.habitat.view.observer.CategoryObserver
import by.aermakova.habitat.view.observer.WeekDayObserver
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams
import java.util.*

class AddNewHabitFragment :
    BaseFragment<FragmentAddNewHabitBinding, AddNewHabitViewModel>(),
    OnTimeSetListener,
    WeekDayObserver,
    CategoryObserver {

    private lateinit var mCategoriesAdapter: CategoryPillsAdapter
    private lateinit var mWeekDayAdapter: WeekDayAdapter

    private var mHours = 10
    private var mMinutes = 0

    override val layoutId: Int
        get() = R.layout.fragment_add_new_habit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setTime(mHours, mMinutes)
        createCategoryRecycler()
        createWeekDaysRecycler()
        binding.back.setOnClickListener { backNavigation() }
        subscribeToNavigationChanged(viewModel)
        createSpinner()
        binding.notificationToggle.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) binding.timeTitle.visibility =
                View.VISIBLE else binding.timeTitle.visibility = View.GONE
            viewModel.setNotificationEnable(isChecked)
        }
        binding.showTimePickerButton.setOnClickListener {
            val timePicker: DialogFragment = TimePickerFragment(mHours, mMinutes)
            timePicker.show(childFragmentManager, TIME_PICKER_TAG)
        }
        setSeekBarSettings()
    }

    private fun setSeekBarSettings() {
        binding.seekBarDuration.onSeekChangeListener = object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams) {}
            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {}
            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {
                viewModel.setDaysCount(seekBar.progress)
            }
        }
    }

    private fun createCategoryRecycler() {
        binding.categoryRecycler.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        mCategoriesAdapter =
            CategoryPillsAdapter(View.OnClickListener { navigateFragment(R.id.action_addNewHabitFragment_to_addNewCategoryFragment) })
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

    private fun createSpinner() {
        val variants: MutableList<String> = ArrayList()
        variants.add(resources.getString(R.string.spinner_every_day))
        variants.add(resources.getString(R.string.spinner_select_manually))

        val spinnerAdapter =
            ArrayAdapter(requireContext(), R.layout.schedule_spinner_row, R.id.variant, variants)
        spinnerAdapter.setDropDownViewResource(R.layout.schedule_spinner_item)
        with(binding.scheduleSpinner) {
            adapter = spinnerAdapter
            onItemSelectedListener = select()
        }
    }

    private fun select(): OnItemSelectedListener {
        return object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    binding.weekDaysRecycler.visibility = View.GONE
                    viewModel.setEveryDay()
                } else {
                    binding.weekDaysRecycler.visibility = View.VISIBLE
                    viewModel.setSelectedWeekDays()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
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

    private fun setCategories() {
        if (arguments != null) {
            val category: Category? = requireArguments().getParcelable(BUNDLE_TAG)
            if (category != null) {
                val categoryList: MutableList<Category> = ArrayList()
                categoryList.add(category)
                mCategoriesAdapter.setCategories(categoryList)
            }
        } else viewModel.allCategories?.observe(
            viewLifecycleOwner,
            Observer { mCategoriesAdapter.setCategories(it) })
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        mHours = hourOfDay
        mMinutes = minute
        var time = "$hourOfDay:$minute"
        if (minute == 0) time += "0"
        binding.timeTitle.text = time
        viewModel.setTime(hourOfDay, minute)
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
        private const val TIME_PICKER_TAG = "habitat_time_picker"
        private const val BUNDLE_TAG = "category"
    }
}