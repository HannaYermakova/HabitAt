package by.aermakova.habitat.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import io.reactivex.Observer

typealias FunctionNoArgs = () -> Unit
typealias FunctionString = (String) -> Unit

@BindingAdapter("app:onClick")
fun clickListener(view: View, listener: FunctionNoArgs?) {
    view.setOnClickListener {
        listener?.invoke()
    }
}

@BindingAdapter("app:tempTitle")
fun editTitleListener(
    editText: EditText,
    tempTitle: Observer<String>?
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            tempTitle?.onNext(editText.text.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}