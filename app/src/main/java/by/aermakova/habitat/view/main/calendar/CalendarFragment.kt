package by.aermakova.habitat.view.main.calendar

import android.os.Bundle
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentCalendarBinding
import by.aermakova.habitat.view.base.BaseFragment

class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_calendar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeOnHabitsWeek()
    }

    private fun subscribeOnHabitsWeek() {
        observe(viewModel.showHabitsWeekUseCase.habits){
            viewModel.showHabitsWeekUseCase.updateHabitsList(it)
        }
        viewModel.loadHabitsList()
    }
}