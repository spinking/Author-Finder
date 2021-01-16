package studio.eyesthetics.authorfinder.ui.authors

import androidx.fragment.app.viewModels
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.app.App
import studio.eyesthetics.authorfinder.ui.base.BaseFragment
import studio.eyesthetics.authorfinder.viewmodels.AuthorsViewModel
import studio.eyesthetics.authorfinder.viewmodels.AuthorsViewModelFactory
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

    override fun setupViews() {

    }
}