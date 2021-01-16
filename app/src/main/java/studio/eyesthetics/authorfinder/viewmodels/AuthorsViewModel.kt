package studio.eyesthetics.authorfinder.viewmodels

import androidx.lifecycle.SavedStateHandle
import studio.eyesthetics.authorfinder.viewmodels.base.BaseViewModel
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import javax.inject.Inject

class AuthorsViewModel(
    handle: SavedStateHandle
) : BaseViewModel<AuthorsState>(handle, AuthorsState()) {

    fun handleSearch(query: String?) {
        query ?: return
        updateState {
            it.copy(searchQuery = query)
        }
    }

    fun handleSearchMode(isSearch: Boolean) {
        updateState {
            it.copy(isSearch = isSearch)
        }
    }
}

class AuthorsViewModelFactory @Inject constructor(

) : IViewModelFactory<AuthorsViewModel> {
    override fun create(handle: SavedStateHandle): AuthorsViewModel {
        return AuthorsViewModel(handle)
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