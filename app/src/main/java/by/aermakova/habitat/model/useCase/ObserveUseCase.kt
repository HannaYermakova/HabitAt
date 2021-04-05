package by.aermakova.habitat.model.useCase

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class ObserveUseCase<T>(
    val adapter: ListAdapter<T, out RecyclerView.ViewHolder>
) {

    fun action(it: List<T>) {
        adapter.submitList(it)
    }

    val what: LiveData<List<T>>
        get() = getList()

    abstract fun getList(): LiveData<List<T>>
}