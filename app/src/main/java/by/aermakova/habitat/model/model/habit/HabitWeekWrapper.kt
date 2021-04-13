package by.aermakova.habitat.model.model.habit

data class HabitWeekWrapper(
    val habit: HabitModel
){
    lateinit var categoryTitle: String
    lateinit var habitsWeek: Array<HabitDayWrapper>
    var requiredDays: Int = 0
    var checkedDays: Int = 0
}