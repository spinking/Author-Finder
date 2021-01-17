package studio.eyesthetics.authorfinder.ui.authors

import android.view.View
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.data.models.AuthorItem
import studio.eyesthetics.authorfinder.data.models.Empty
import studio.eyesthetics.authorfinder.ui.base.BaseAdapterDelegate

class EmptyDelegate : BaseAdapterDelegate<AuthorItem>() {
    override val layoutRes: Int = R.layout.item_empty

    override fun createHolder(view: View): ViewHolder = EmptyViewHolder(view)

    override fun onBindViewHolder(item: AuthorItem, holder: ViewHolder, payloads: MutableList<Any>) {}

    override fun isForItem(item: AuthorItem, items: MutableList<AuthorItem>, position: Int): Boolean = item is Empty

    inner class EmptyViewHolder(convertView: View) : ViewHolder(convertView)
}