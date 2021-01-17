package studio.eyesthetics.authorfinder.ui.author

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sysdata.widget.accordion.ExpandableItemHolder
import com.sysdata.widget.accordion.ExpandedViewHolder
import com.sysdata.widget.accordion.ItemAdapter
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.data.models.Description
import studio.eyesthetics.authorfinder.data.models.DescriptionItem
import studio.eyesthetics.authorfinder.ui.base.DelegationAdapter

class AuthorExpandedViewHolder private constructor(
    itemView: View
) : ExpandedViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.tv_expanded_title)
    private val rvFilter = itemView.findViewById<RecyclerView>(R.id.rv_expanded_item)
    private val itemsAdapter by lazy { DelegationAdapter<DescriptionItem>() }

    override fun onBindItemView(itemHolder: ExpandableItemHolder<*>) {
        val item = itemHolder.item as Description
        itemsAdapter.delegatesManager.apply {
            addDelegate(TitleDelegate())
            addDelegate(WorkDelegate())
        }

        rvFilter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
        title.text = item.title
        itemsAdapter.items = item.items
    }

    override fun onRecycleItemView() {}

    override fun getViewHolderFactory(): ItemAdapter.ItemViewHolder.Factory? = null

    class Factory(
        @field:LayoutRes @param:LayoutRes private val mItemViewLayoutId: Int
    ) : ItemAdapter.ItemViewHolder.Factory {

        override fun createViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder<*> {
            val itemView: View = LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            return AuthorExpandedViewHolder(itemView)
        }

        override fun getItemViewLayoutId(): Int = mItemViewLayoutId

        companion object {
            fun create(@LayoutRes itemViewLayoutId: Int): Factory {
                return Factory(itemViewLayoutId)
            }
        }
    }
}