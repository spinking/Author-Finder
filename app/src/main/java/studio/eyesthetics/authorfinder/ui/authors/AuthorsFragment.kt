package studio.eyesthetics.authorfinder.ui.authors

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_authors.*
import kotlinx.android.synthetic.main.search_view_layout.view.*
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.app.App
import studio.eyesthetics.authorfinder.data.models.Author
import studio.eyesthetics.authorfinder.ui.base.*
import studio.eyesthetics.authorfinder.viewmodels.AuthorsState
import studio.eyesthetics.authorfinder.viewmodels.AuthorsViewModel
import studio.eyesthetics.authorfinder.viewmodels.AuthorsViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import studio.eyesthetics.authorfinder.viewmodels.base.SavedStateViewModelFactory
import javax.inject.Inject

class AuthorsFragment : BaseFragment<AuthorsViewModel>() {

    init {
        App.INSTANCE.appComponent.inject(this@AuthorsFragment)
    }

    @Inject
    internal lateinit var authorsViewModelFactory: AuthorsViewModelFactory

    override val layout: Int = R.layout.fragment_authors
    override val viewModel: AuthorsViewModel by viewModels {
        SavedStateViewModelFactory(authorsViewModelFactory, this)
    }
    override val binding: AuthorsBinding by lazy { AuthorsBinding() }
    override val prepareToolbar: (ToolbarBuilder.() -> Unit)? = {
        this.setBackButtonVisible(false)
            .setTitle(getString(R.string.app_name))
            .addMenuItem(
                MenuItemHolder(
                    getString(R.string.authors_search_title),
                    R.id.action_search,
                    R.drawable.ic_baseline_search_24,
                    R.layout.search_view_layout
                )
            )
    }

    private val authorAdapter by lazy { DelegationAdapter<Author>() }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        if (binding.isSearch) {
            menuItem.expandActionView()
            searchView.setQuery(binding.searchQuery, false)
        }

        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                viewModel.handleSearchMode(true)
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                viewModel.handleSearchMode(false) // or true?
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.handleSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.handleSearch(newText)
                return true
            }
        })

        searchView.setOnCloseListener {
            viewModel.handleSearchMode(false)
            true
        }
    }

    override fun setupViews() {
        setHasOptionsMenu(true)
        viewModel.observeAuthors(viewLifecycleOwner) {
            authorAdapter.items = it
        }
        initAdapter()
    }

    private fun initAdapter() {
        authorAdapter.delegatesManager.addDelegate(AuthorDelegate {
            //TODO transition to single author
        })

        rv_authors.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = authorAdapter
        }
    }

    override fun onDestroyView() {
        toolbar.search_view?.setOnQueryTextListener(null)
        rv_authors.adapter = null
        super.onDestroyView()
    }

    inner class AuthorsBinding : Binding() {
        var searchQuery: String? = null
        var isSearch = false

        override fun bind(data: IViewModelState) {
            data as AuthorsState
            isSearch = data.isSearch
            searchQuery = data.searchQuery
        }
    }
}