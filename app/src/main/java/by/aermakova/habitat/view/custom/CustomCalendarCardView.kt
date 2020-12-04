package by.aermakova.habitat.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.aermakova.habitat.databinding.CalendarCustomCardBinding
import by.aermakova.habitat.view.custom.dataadapter.CalendarDayAdapter
import by.aermakova.habitat.view.custom.layoutmanager.SpanningLinearLayoutManager

class CustomCalendarCardView(context: Context, attrs: AttributeSet?) : CardView(context) {
    private var mContext: Context = context
    private var mBinding: CalendarCustomCardBinding
    private var calendarDayAdapter: CalendarDayAdapter

    init {
        val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        mBinding = CalendarCustomCardBinding.inflate(inflater, this, false)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        elevation = 10f

        mBinding.daysRecycler.layoutManager = SpanningLinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        calendarDayAdapter = CalendarDayAdapter()
        mBinding.daysRecycler.adapter = calendarDayAdapter

    }
}