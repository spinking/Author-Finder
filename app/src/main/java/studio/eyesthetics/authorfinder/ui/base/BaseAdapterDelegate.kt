package studio.eyesthetics.authorfinder.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

abstract class BaseAdapterDelegate<T> : AbsListItemAdapterDelegate<T, T, BaseAdapterDelegate.ViewHolder>() {
    protected var listSize = 0
    protected var currentPosition = 0

    abstract val layoutRes: Int
    abstract fun createHolder(view: View): ViewHolder
    open fun isForItem(item: T, items: MutableList<T>, position: Int): Boolean = item is T

    open class ViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layoutRes, parent, false)
        return createHolder(view)
    }

    override fun isForViewType(item: T, items: MutableList<T>, position: Int): Boolean {
        listSize = items.size
        currentPosition = position
        return isForItem(item, items, position)
    }
}