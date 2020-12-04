package by.aermakova.habitat.view.observer

interface ColorObservable {
    fun registerObserver(o: ColorObserver)
    fun unregisterObserver(o: ColorObserver)
    fun notifyObserver(color: Int)
}