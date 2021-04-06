package by.aermakova.habitat.view.custom.dialog

import android.app.Activity
import android.app.TimePickerDialog
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import dagger.Module
import dagger.Provides

@Module
class TimePickerModule {

    @Provides
    fun provideDialogNavigation(activity: Activity): TimePickerDialog.OnTimeSetListener {
        val navController = Navigation.findNavController(activity, R.id.app_host_fragment)
        return TimePickerNavigation(navController)
    }
}