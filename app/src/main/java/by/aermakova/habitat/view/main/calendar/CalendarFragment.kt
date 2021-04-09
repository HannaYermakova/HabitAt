package by.aermakova.habitat.view.main.calendar

import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentCalendarBinding
import by.aermakova.habitat.view.base.BaseFragment

class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_calendar
}