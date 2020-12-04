package by.aermakova.habitat.view.main.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.aermakova.habitat.R

class CalendarFragment : Fragment() {
//    private var homeViewModel: CalendarViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        homeViewModel = ViewModelProviders.of(this).get(CalendarViewModel::class.java)
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }
}