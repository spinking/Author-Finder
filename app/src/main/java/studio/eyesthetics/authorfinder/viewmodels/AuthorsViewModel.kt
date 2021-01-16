package studio.eyesthetics.authorfinder.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import studio.eyesthetics.authorfinder.data.models.Author
import studio.eyesthetics.authorfinder.data.repositories.IAuthorRepository
import studio.eyesthetics.authorfinder.viewmodels.base.BaseViewModel
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import javax.inject.Inject

class AuthorsViewModel(
    handle: SavedStateHandle,
    private val authorRepository: IAuthorRepository
) : BaseViewModel<AuthorsState>(handle, AuthorsState()) {

    private val authors = MutableLiveData<List<Author>>()

    fun observeAuthors(owner: LifecycleOwner, onChange: (List<Author>) -> Unit) {
        authors.observe(owner, Observer { onChange(it) })
    }

    private fun getAuthors(query: String) {
        launchSafety {
            val response = authorRepository.getAuthors(query)
            authors.value = response
        }
    }

    fun handleSearch(query: String?) {
        query ?: return
        updateState {
            it.copy(searchQuery = query)
        }
        if (query.isNotEmpty())
            getAuthors(query)
    }

    fun handleSearchMode(isSearch: Boolean) {
        updateState {
            it.copy(isSearch = isSearch)
        }
    }
}

class AuthorsViewModelFactory @Inject constructor(
    private val authorRepository: IAuthorRepository
) : IViewModelFactory<AuthorsViewModel> {
    override fun create(handle: SavedStateHandle): AuthorsViewModel {
        return AuthorsViewModel(handle, authorRepository)
    }
}

data class AuthorsState(
    val isSearch: Boolean = false,
    val searchQuery: String? = null
) : IViewModelState {
    override fun save(outState: SavedStateHandle) {
        outState.set(::isSearch.name, isSearch)
        outState.set(::searchQuery.name, searchQuery)
    }

    override fun restore(savedState: SavedStateHandle): IViewModelState {
        return this.copy(
            isSearch = savedState.get<Boolean>(::isSearch.name) ?: false,
            searchQuery = savedState.get<String>(::searchQuery.name)
        )
    }
}