package by.aermakova.habitat.view.custom

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment

class TimePickerFragment(private val hour: Int, private val minute: Int) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(requireActivity(), parentFragment as OnTimeSetListener?,
                hour, minute, DateFormat.is24HourFormat(requireContext()))
    }
}