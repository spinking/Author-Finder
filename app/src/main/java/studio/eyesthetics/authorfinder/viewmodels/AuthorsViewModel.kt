package studio.eyesthetics.authorfinder.viewmodels

import androidx.lifecycle.SavedStateHandle
import studio.eyesthetics.authorfinder.viewmodels.base.BaseViewModel
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import javax.inject.Inject

class AuthorsViewModel(
    handle: SavedStateHandle
) : BaseViewModel<AuthorsState>(handle, AuthorsState()) {

}

class AuthorsViewModelFactory @Inject constructor(

) : IViewModelFactory<AuthorsViewModel> {
    override fun create(handle: SavedStateHandle): AuthorsViewModel {
        return AuthorsViewModel(handle)
    }
}

class AuthorsState : IViewModelState