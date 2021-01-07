package by.aermakova.habitat.view.onboarding.signup

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.R
import by.aermakova.habitat.util.SingleLiveEvent
import javax.inject.Inject

class SignUpViewModel @Inject constructor() : ViewModel() {
    @kotlin.jvm.JvmField
    var userName: String? = null
    val saveUserNameCommand: SingleLiveEvent<String?> = SingleLiveEvent()
    val showErrorMessageCommand: SingleLiveEvent<Int?> = SingleLiveEvent()

    fun saveUserName() {
        if (!TextUtils.isEmpty(userName!!.trim { it <= ' ' })) {
            saveUserNameCommand.setValue(userName!!.trim { it <= ' ' })
        } else showErrorMessageCommand.setValue(R.string.error_empty_name)
    }
}