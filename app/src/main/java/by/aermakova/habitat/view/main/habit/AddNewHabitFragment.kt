package by.aermakova.habitat.view.main.habit

import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

class AddNewHabitFragment : BaseFragment<FragmentAddNewHabitBinding, AddNewHabitViewModel>(), OnTimeSetListener, WeekDayObserver, CategoryObserver {
    private lateinit var mBinding: FragmentAddNewHabitBinding
    private lateinit var mViewModel: AddNewHabitViewModel
    private lateinit var mCategoriesAdapter: CategoryPillsAdapter
    private lateinit var mWeekDayAdapter: WeekDayAdapter
    private var mHours = 10
    private var mMinutes = 0

    override val layoutId: Int
        get() = R.layout.fragment_add_new_habit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_habit, container, false)
        mViewModel = ViewModelProvider(this).get(AddNewHabitViewModel::class.java)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.setTime(mHours, mMinutes)
        mBinding.viewModel = mViewModel
        createCategoryRecycler()
        createWeekDaysRecycler()
        mBinding.back.setOnClickListener { backNavigation() }
        subscribeToNavigationChanged(mViewModel)
        createSpinner()
        mBinding.setNotification.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) mBinding.setTime.visibility = View.VISIBLE else mBinding.setTime.visibility = View.GONE
            mViewModel.setNotificationEnable(isChecked)
        }
        mBinding.showTimePickerButton.setOnClickListener {
            val timePicker: DialogFragment = TimePickerFragment(mHours, mMinutes)
            timePicker.show(childFragmentManager, TIME_PICKER_TAG)
        }
        setSeekBarSettings()
    }

    private fun setSeekBarSettings() {
        mBinding.seekBarDuration.onSeekChangeListener = object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams) {}
            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {}
            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {
                mViewModel.setDaysCount(seekBar.progress)
            }
        }
    }

    private fun createCategoryRecycler() {
        mBinding.categoryRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        mCategoriesAdapter = CategoryPillsAdapter(View.OnClickListener { navigateFragment(R.id.action_addNewHabitFragment_to_addNewCategoryFragment) })
        mCategoriesAdapter.registerObserver(this)
        mBinding.categoryRecycler.adapter = mCategoriesAdapter
        setCategories()
    }

    private fun createWeekDaysRecycler() {
        mBinding.weekDaysRecycler.layoutManager = SpanningLinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        mWeekDayAdapter = WeekDayAdapter()
        mWeekDayAdapter.registerObserver(this)
        mBinding.weekDaysRecycler.adapter = mWeekDayAdapter
    }

    private fun createSpinner() {
        val variants: MutableList<String> = ArrayList()
        variants.add(resources.getString(R.string.spinner_every_day))
        variants.add(resources.getString(R.string.spinner_select_manually))

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.schedule_spinner_row, R.id.variant, variants)
        spinnerAdapter.setDropDownViewResource(R.layout.schedule_spinner_item)
        with(mBinding.scheduleSpinner) {
            adapter = spinnerAdapter
            onItemSelectedListener = select()
        }
    }

    private fun select(): OnItemSelectedListener {
        return object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    mBinding.weekDaysRecycler.visibility = View.GONE
                    mViewModel.setEveryDay()
                } else {
                    mBinding.weekDaysRecycler.visibility = View.VISIBLE
                    mViewModel.setSelectedWeekDays()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun subscribeToNavigationChanged(viewModel: AddNewHabitViewModel) {
        viewModel.showErrorMessageCommand.observe(viewLifecycleOwner, Observer { message: Int? -> showSnackBarMessage(message!!) })
        viewModel.saveHabitCommand.observe(viewLifecycleOwner, Observer { backNavigation() })
        viewModel.setNotificationLogicCommand.observe(viewLifecycleOwner, Observer { habit: Habit? -> createNotificationLogic(habit) })
    }

    private fun createNotificationLogic(habit: Habit?) {
        if (mBinding.setNotification.isChecked) {
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
        } else mViewModel.allCategories?.observe(viewLifecycleOwner, Observer { mCategoriesAdapter.setCategories(it) })
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        mHours = hourOfDay
        mMinutes = minute
        var time = "$hourOfDay:$minute"
        if (minute == 0) time += "0"
        mBinding.timeText.text = time
        mViewModel.setTime(hourOfDay, minute)
    }

    override fun updateCategory(categoryId: Long) {
        mViewModel.setCategoryId(categoryId)
    }

    override fun updateWeekDays(days: BooleanArray?) {
        mViewModel.setWeekDays(days!!)
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