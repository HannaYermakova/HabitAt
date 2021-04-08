package by.aermakova.habitat.model.useCase

import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class SaveNewHabitUseCase(
    private val dataBase: AppDataBase
) {

    val saveHabitCommand: SingleLiveEvent<Habit> = SingleLiveEvent()

    fun saveHabit(
        habit: Habit?,
        scope: CoroutineScope,
        errorAction: () -> Unit
    ) {
        habit?.let {
            scope.launch {
                try {
                    dataBase.habitDao().insert(habit)
                    saveHabitCommand.value = habit
                    saveHabitCommand.call()
                } catch (e: Exception) {
                    e.printStackTrace()
                    errorAction.invoke()
                }
            }
        }
    }
}