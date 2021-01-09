package by.aermakova.habitat.model.useCase

import by.aermakova.habitat.model.db.dao.HabitDao
import by.aermakova.habitat.model.db.entity.Habit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class GetListOfHabitsUseCase(
    private val habitDao: HabitDao
) {

    private val _habitsList = PublishSubject.create<List<Habit>>()
    val habits: Observable<List<Habit>>
        get() = _habitsList

    fun get(disposable: CompositeDisposable) {
        disposable.add(
            habitDao.getAllHabits()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _habitsList.onNext(it) },
                    { it.printStackTrace() }
                )
        )
    }
}