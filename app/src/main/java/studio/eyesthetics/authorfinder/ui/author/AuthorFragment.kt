package studio.eyesthetics.authorfinder.ui.author

import androidx.fragment.app.viewModels
import studio.eyesthetics.authorfinder.R
import studio.eyesthetics.authorfinder.app.App
import studio.eyesthetics.authorfinder.ui.base.BaseFragment
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

    override fun setupViews() {

    }
}