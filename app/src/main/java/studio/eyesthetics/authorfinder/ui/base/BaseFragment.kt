package studio.eyesthetics.authorfinder.ui.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import studio.eyesthetics.authorfinder.MainActivity
import studio.eyesthetics.authorfinder.viewmodels.base.BaseViewModel
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import studio.eyesthetics.authorfinder.viewmodels.base.Loading
import studio.eyesthetics.authorfinder.viewmodels.base.Notify

abstract class BaseFragment<T : BaseViewModel<out IViewModelState>> : Fragment() {
    val main: MainActivity
        get() = activity as MainActivity
    open val binding: Binding? = null
    protected abstract val viewModel: T
    protected abstract val layout: Int

    abstract fun setupViews()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.restoreState()

        viewModel.observeState(viewLifecycleOwner) { binding?.bind(it) }
        if(binding?.isInflated == false) binding?.onFinishInflate()

        viewModel.observeNotifications(viewLifecycleOwner) { main.renderNotification(it) }
        viewModel.observeNavigation(viewLifecycleOwner) { main.viewModel.navigate(it) }
        viewModel.observeLoading(viewLifecycleOwner) { renderLoading(it) }
        binding?.restoreUi(savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupViews()
        binding?.rebind()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        binding?.saveUi(outState)
        super.onSaveInstanceState(outState)
    }

    open fun renderNotification(notify: Notify) {
        main.renderNotification(notify)
    }

    private fun renderLoading(loadingState: Loading) {
        main.renderLoading(loadingState)
    }
}