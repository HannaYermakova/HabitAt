package by.aermakova.habitat.view.main.category.addNew

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import by.aermakova.habitat.R
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.useCase.SelectColorUseCase
import by.aermakova.habitat.util.SingleLiveEvent
import by.aermakova.habitat.view.base.BaseViewModel
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddNewCategoryViewModel @Inject constructor(
    val selectColorUseCase: SelectColorUseCase,
    private val dataBase: AppDataBase
) : BaseViewModel() {

    @kotlin.jvm.JvmField
    var title: String? = null
    val saveCategoryCommand: SingleLiveEvent<Void?> = SingleLiveEvent()

    fun saveCategory() {
        if (TextUtils.isEmpty(title) || selectColorUseCase.selectedColor == null) {
            showErrorMessageCommand.setValue(R.string.error_empty_fields)
        } else {
            Completable.fromAction {
                dataBase.categoryDao()
                    .insert(Category(title, 0, selectColorUseCase.selectedColor!!.cardColor.id))
            }
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

    fun loadColors() {
        selectColorUseCase.loadColors(viewModelScope)
    }
}