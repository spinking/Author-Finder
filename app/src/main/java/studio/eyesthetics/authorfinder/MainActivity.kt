package studio.eyesthetics.authorfinder

import android.graphics.Rect
import android.view.MotionEvent
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import com.google.android.material.textfield.TextInputEditText
import studio.eyesthetics.authorfinder.app.App
import studio.eyesthetics.authorfinder.app.extensions.hideSoftKeyboard
import studio.eyesthetics.authorfinder.ui.base.BaseActivity
import studio.eyesthetics.authorfinder.viewmodels.MainViewModel
import studio.eyesthetics.authorfinder.viewmodels.MainViewModelFactory
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState
import studio.eyesthetics.authorfinder.viewmodels.base.Notify
import studio.eyesthetics.authorfinder.viewmodels.base.SavedStateViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    init {
        App.INSTANCE.appComponent.inject(this@MainActivity)
    }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override val layout: Int = R.layout.activity_main
    public override val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(mainViewModelFactory, this)
    }

    override fun subscribeOnState(state: IViewModelState) {
        // DO something
    }

    override fun renderNotification(notify: Notify) {
        when (notify) {
            is Notify.TextMessage -> {
                Toast.makeText(this, notify.message, Toast.LENGTH_SHORT).show()
            }
            is Notify.ErrorMessage -> {
                val builder = AlertDialog.Builder(this)
                builder.apply {
                    setTitle(notify.errLabel)
                    setMessage(notify.message)
                    setPositiveButton(
                        getString(R.string.dialog_positive_button)
                    ) { dialog, id ->
                        notify.errHandler?.invoke()
                        dialog.dismiss()
                    }
                }
                builder.show()
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (
                v is SearchView.SearchAutoComplete ||
                v is EditText ||
                v is TextInputEditText
            ) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    v.clearFocus()
                    v.hideSoftKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}