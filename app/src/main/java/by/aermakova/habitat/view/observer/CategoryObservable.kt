package by.aermakova.habitat.view.observer

interface CategoryObservable {
    fun registerObserver(o: CategoryObserver)
    fun unregisterObserver(o: CategoryObserver)
    fun notifyObserverCategory(categoryId: Long)
}