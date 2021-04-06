package by.aermakova.habitat.view.custom.dialog

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.navigation.NavController
import by.aermakova.habitat.AppNavigationDirections
import by.aermakova.habitat.model.model.TimeModel
import by.aermakova.habitat.model.model.toTimeModel
import by.aermakova.habitat.view.navigation.DialogNavigation


class TimePickerNavigation(private val controller: NavController) :
    TimePickerDialog.OnTimeSetListener,
    DialogNavigation<TimeModel> {

    companion object {
        private const val DIALOG_RESULT = "pick_time_dialog_result"
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        setDialogResult(TimeModel(hourOfDay, minute))
    }

    override fun openItemDialog(title: String) {
        controller.navigate(AppNavigationDirections.actionGlobalTimePickerFragment())
    }

    override fun getDialogResult(): LiveData<TimeModel> {
        val liveData = controller.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(DIALOG_RESULT)
        return liveData?.let { map(liveData) { it.toTimeModel() } } ?: MutableLiveData()
    }

    override fun setDialogResult(result: TimeModel) {
        controller.previousBackStackEntry?.savedStateHandle?.set(DIALOG_RESULT, result.toStringModel())
    }
}