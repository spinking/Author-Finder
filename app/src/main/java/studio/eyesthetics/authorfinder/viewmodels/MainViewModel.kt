package studio.eyesthetics.authorfinder.viewmodels

import androidx.lifecycle.SavedStateHandle
import studio.eyesthetics.authorfinder.viewmodels.base.BaseViewModel
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import javax.inject.Inject

class MainViewModel(
    handle: SavedStateHandle
) : BaseViewModel<MainState>(handle, MainState()) {

}

class MainViewModelFactory @Inject constructor(

) : IViewModelFactory<MainViewModel> {
    override fun create(handle: SavedStateHandle): MainViewModel {
        return MainViewModel(handle)
    }
}

class MainState : IViewModelState