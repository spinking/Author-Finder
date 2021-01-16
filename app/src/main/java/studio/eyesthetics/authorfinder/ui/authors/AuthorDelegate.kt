package studio.eyesthetics.authorfinder.ui.authors

import android.view.View
import kotlinx.android.synthetic.main.item_author.*
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.data.models.Author
import studio.eyesthetics.authorfinder.ui.base.BaseAdapterDelegate

class AuthorDelegate(
    private val listener: (String) -> Unit
) : BaseAdapterDelegate<Author>() {
    override val layoutRes: Int = R.layout.item_author

    override fun createHolder(view: View): ViewHolder = AuthorViewHolder(view)

    override fun onBindViewHolder(item: Author, holder: ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as AuthorViewHolder
        viewHolder.bind(item)
    }

    inner class AuthorViewHolder(convertView: View) : ViewHolder(convertView) {
        fun bind(item: Author) {
            tv_author_id.text = item.authorid
            tv_author_name.text = item.authordisplay
            itemView.setOnClickListener {
                listener.invoke(item.authorid)
            }
        }
    }
}