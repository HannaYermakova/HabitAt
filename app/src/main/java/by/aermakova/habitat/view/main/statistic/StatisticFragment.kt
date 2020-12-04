package by.aermakova.habitat.view.main.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.aermakova.habitat.R

class StatisticFragment : Fragment() {
    private var notificationsViewModel: StatisticViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notificationsViewModel = ViewModelProviders.of(this).get(StatisticViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statistic, container, false)
        val textView = root.findViewById<TextView>(R.id.text_notifications)
        notificationsViewModel?.text?.observe(viewLifecycleOwner, Observer { s -> textView.text = s })
        return root
    }
}