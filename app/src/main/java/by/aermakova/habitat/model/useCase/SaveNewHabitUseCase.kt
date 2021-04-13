package by.aermakova.habitat.model.useCase

import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.model.habit.HabitModel
import by.aermakova.habitat.model.model.habit.toEntity
import by.aermakova.habitat.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class SaveNewHabitUseCase(
    private val dataBase: AppDataBase
) {

    val saveHabitCommand: SingleLiveEvent<HabitModel> = SingleLiveEvent()

    fun saveHabit(
        habit: HabitModel?,
        scope: CoroutineScope,
        errorAction: () -> Unit
    ) {
        habit?.let {
            scope.launch {
                try {
                    dataBase.habitDao().insert(habit.toEntity())
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