package by.aermakova.habitat.view.observer

interface WeekDayObservable {
    fun registerObserver(o: WeekDayObserver)
    fun unregisterObserver(o: WeekDayObserver)
    fun notifyObserverWeekDays(days: BooleanArray?)
}