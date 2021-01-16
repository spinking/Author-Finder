package studio.eyesthetics.authorfinder.ui.base

import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class DelegationAdapter<T> : ListDelegationAdapter<List<T>>(AdapterDelegatesManager()) {

    val delegatesManager: AdapterDelegatesManager<List<T>>
        get() = super.delegatesManager

    init {
        items = listOf()
    }

    override fun setItems(items: List<T>) = setItems(items, null)

    fun setItems(items: List<T>, payload: Any? = null) {
        val oldSize = this.items.size
        val newSize = items.size
        super.setItems(items)
        when {
            oldSize > newSize -> {
                notifyItemRangeRemoved(newSize, oldSize - newSize)
                notifyItemRangeChanged(0, newSize, payload)
            }
            newSize > oldSize -> {
                notifyItemRangeInserted(oldSize, newSize - oldSize)
                notifyItemRangeChanged(0, oldSize, payload)
            }
            else -> notifyItemRangeChanged(0, newSize, payload)
        }
    }
}