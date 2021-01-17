package studio.eyesthetics.authorfinder.ui.author

import androidx.fragment.app.viewModels
import com.sysdata.widget.accordion.ExpandableItemHolder
import com.sysdata.widget.accordion.ItemAdapter
import kotlinx.android.synthetic.main.fragment_author.*
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.app.App
import studio.eyesthetics.authorfinder.data.models.*
import studio.eyesthetics.authorfinder.ui.base.BaseFragment
import studio.eyesthetics.authorfinder.ui.base.ToolbarBuilder
import studio.eyesthetics.authorfinder.viewmodels.AuthorViewModel
import studio.eyesthetics.authorfinder.viewmodels.AuthorViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.SavedStateViewModelFactory
import javax.inject.Inject

class AuthorFragment : BaseFragment<AuthorViewModel>() {

    init {
        App.INSTANCE.appComponent.inject(this@AuthorFragment)
    }

    @Inject
    internal lateinit var authorViewModelFactory: AuthorViewModelFactory

    override val layout: Int = R.layout.fragment_author
    override val viewModel: AuthorViewModel by viewModels {
        SavedStateViewModelFactory(authorViewModelFactory, this)
    }

    override val prepareToolbar: (ToolbarBuilder.() -> Unit)? = {
        this.setTitle(getString(R.string.author_label))
    }

    override fun setupViews() {

        viewModel.observeAuthor(viewLifecycleOwner) {
            initAuthor(it)
        }

        initAdapters()

        val authorId = requireArguments().getString(AUTHOR_ID) ?: ""
        if (authorId.isNotEmpty())
            viewModel.getAuthorById(authorId)
    }

    private fun initAdapters() {

        val listener = ItemAdapter.OnItemClickedListener {viewHolder, id ->  }

        rv_description.apply {
            setCollapsedViewHolderFactory(
                AuthorCollapsedViewHolder.Factory.create(R.layout.accordion_collapsed_item),
                listener
            )
            setExpandedViewHolderFactory(
                AuthorExpandedViewHolder.Factory.create(R.layout.accordion_expanded_item),
                listener
            )
        }
    }

    private fun initAuthor(author: SingleAuthor) {
        val authorId = "${getString(R.string.authors_id)}: ${author.authorid}"
        val authorName = "${getString(R.string.authors_name)}: ${author.authordisplay}"
        tv_author_id.text = authorId
        tv_author_name.text = authorName
        updateAdapter(author.items)
    }

    private fun updateAdapter(items: List<Description>) {
        val itemHolders: MutableList<ExpandableItemHolder<*>> = ArrayList()
        itemHolders.addAll(items.map { ExpandableItemHolder(it) })
        rv_description.setAdapterItems(itemHolders)
    }

    companion object {
        const val AUTHOR_ID = "author_id"
    }
}