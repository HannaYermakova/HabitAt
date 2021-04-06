package by.aermakova.habitat.view.custom.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import by.aermakova.habitat.view.base.BaseDialogFragment
import javax.inject.Inject

class TimePickerFragment :
    BaseDialogFragment(),
    OnTimeSetListener {

    @Inject
    lateinit var listener: OnTimeSetListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(
            requireActivity(),
            this,
            10,
            0,
            DateFormat.is24HourFormat(requireContext())
        )
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        listener.onTimeSet(view, hourOfDay, minute)
    }
}