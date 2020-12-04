package by.aermakova.habitat.view.main.category.addNew

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.util.SingleLiveEvent
import by.aermakova.habitat.view.app.App
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddNewCategoryViewModel : ViewModel() {
    private val dataBase: AppDataBase = App.component!!.dataBase

    @kotlin.jvm.JvmField
    var title: String? = null
    private var color = 0
    val saveCategoryCommand: SingleLiveEvent<Void?> = SingleLiveEvent()
    val showErrorMessageCommand: SingleLiveEvent<Int?> = SingleLiveEvent()

    fun saveCategory() {
        if (TextUtils.isEmpty(title) || color == 0) {
            showErrorMessageCommand.setValue(R.string.error_empty_fields)
        } else {
            Completable.fromAction { dataBase.categoryDao().insert(Category(title, 0, color)) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : CompletableObserver {
                        override fun onSubscribe(d: Disposable) {}
                        override fun onComplete() {
                            saveCategoryCommand.call()
                        }

                        override fun onError(e: Throwable) {
                            showErrorMessageCommand.value = R.string.error_db
                        }
                    })
        }
    }

    fun setSelectedColor(color: Int) {
        this.color = color
    }
}