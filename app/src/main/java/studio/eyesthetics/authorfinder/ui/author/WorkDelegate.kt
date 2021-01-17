package studio.eyesthetics.authorfinder.ui.author

import android.view.View
import kotlinx.android.synthetic.main.item_work.*
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.data.models.DescriptionItem
import studio.eyesthetics.authorfinder.data.models.Work
import studio.eyesthetics.authorfinder.ui.base.BaseAdapterDelegate

class WorkDelegate : BaseAdapterDelegate<DescriptionItem>() {
    override val layoutRes: Int = R.layout.item_work

    override fun createHolder(view: View): ViewHolder = WorkViewHolder(view)

    override fun onBindViewHolder(item: DescriptionItem, holder: ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as WorkViewHolder
        viewHolder.bind(item as Work)
    }

    override fun isForItem(item: DescriptionItem, items: MutableList<DescriptionItem>, position: Int): Boolean = item is Work

    inner class WorkViewHolder(convertView: View) : ViewHolder(convertView) {
        fun bind(item: Work) {
            val workText = "${itemView.context.getString(R.string.author_work_id)}: ${item.title}"
            tv_work.text = workText
        }
    }
}