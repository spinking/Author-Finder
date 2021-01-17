package studio.eyesthetics.authorfinder.ui.author

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.sysdata.widget.accordion.CollapsedViewHolder
import com.sysdata.widget.accordion.ExpandableItemHolder
import com.sysdata.widget.accordion.ItemAdapter
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.data.models.Description

class AuthorCollapsedViewHolder private constructor(
    itemView: View
) : CollapsedViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.tv_collapsed_title)

    override fun onBindItemView(itemHolder: ExpandableItemHolder<*>) {
        val item = itemHolder.item as Description
        title.text = item.title
    }

    override fun onRecycleItemView() {}

    override fun getViewHolderFactory(): Factory? = null

    class Factory internal constructor(@field:LayoutRes @param:LayoutRes private val mItemViewLayoutId: Int) :
        ItemAdapter.ItemViewHolder.Factory {

        override fun createViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder<*> {
            val itemView: View = LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            return AuthorCollapsedViewHolder(itemView)
        }

        override fun getItemViewLayoutId(): Int = mItemViewLayoutId

        companion object {
            fun create(@LayoutRes itemViewLayoutId: Int): Factory {
                return Factory(itemViewLayoutId)
            }
        }
    }
}
