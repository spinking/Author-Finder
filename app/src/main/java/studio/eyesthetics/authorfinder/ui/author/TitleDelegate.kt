package studio.eyesthetics.authorfinder.ui.author

import android.view.View
import kotlinx.android.synthetic.main.item_title.*
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.data.models.DescriptionItem
import studio.eyesthetics.authorfinder.data.models.Isbn
import studio.eyesthetics.authorfinder.ui.base.BaseAdapterDelegate

class TitleDelegate : BaseAdapterDelegate<DescriptionItem>() {
    override val layoutRes: Int = R.layout.item_title

    override fun createHolder(view: View): ViewHolder = TitleViewHolder(view)

    override fun onBindViewHolder(item: DescriptionItem, holder: ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as TitleViewHolder
        viewHolder.bind(item as Isbn)
    }

    override fun isForItem(item: DescriptionItem, items: MutableList<DescriptionItem>, position: Int): Boolean = item is Isbn

    inner class TitleViewHolder(convertView: View) : ViewHolder(convertView) {
        fun bind(item: Isbn) {
            val contributorText = "${itemView.context.getString(R.string.author_contributor)}: ${item.contributorType}"
            tv_contributor_type.text = contributorText
            val isbn = "${itemView.context.getString(R.string.author_number)}: ${item.contributorId}"
            tv_number.text = isbn
        }
    }
}