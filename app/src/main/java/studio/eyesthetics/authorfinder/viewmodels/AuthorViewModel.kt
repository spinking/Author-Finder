package studio.eyesthetics.authorfinder.viewmodels

import androidx.lifecycle.SavedStateHandle
import studio.eyesthetics.authorfinder.viewmodels.base.BaseViewModel
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import javax.inject.Inject

class AuthorViewModel(
    handle: SavedStateHandle
) : BaseViewModel<AuthorState>(handle, AuthorState()) {

}

class AuthorViewModelFactory @Inject constructor(

) : IViewModelFactory<AuthorViewModel> {
    override fun create(handle: SavedStateHandle): AuthorViewModel {
        return AuthorViewModel(handle)
    }
}

class AuthorState : IViewModelState