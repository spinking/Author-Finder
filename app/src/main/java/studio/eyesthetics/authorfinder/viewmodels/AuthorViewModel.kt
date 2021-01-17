package studio.eyesthetics.authorfinder.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import studio.eyesthetics.authorfinder.data.models.SingleAuthor
import studio.eyesthetics.authorfinder.data.repositories.IAuthorRepository
import studio.eyesthetics.authorfinder.viewmodels.base.BaseViewModel
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import javax.inject.Inject

class AuthorViewModel(
    handle: SavedStateHandle,
    private val authorRepository: IAuthorRepository
) : BaseViewModel<AuthorState>(handle, AuthorState()) {

    private val author = MutableLiveData<SingleAuthor>()

    fun observeAuthor(owner: LifecycleOwner, onChange: (SingleAuthor) -> Unit) {
        author.observe(owner, Observer { onChange(it) })
    }

    fun getAuthorById(authorId: String) {
        launchSafety {
            val response = authorRepository.getAuthorById(authorId)
            author.value = response
        }
    }
}

class AuthorViewModelFactory @Inject constructor(
    private val authorRepository: IAuthorRepository
) : IViewModelFactory<AuthorViewModel> {
    override fun create(handle: SavedStateHandle): AuthorViewModel {
        return AuthorViewModel(handle, authorRepository)
    }
}

class AuthorState : IViewModelState