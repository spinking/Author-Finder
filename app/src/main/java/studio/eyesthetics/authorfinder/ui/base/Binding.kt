package studio.eyesthetics.authorfinder.ui.base

import android.os.Bundle
import studio.eyesthetics.authorfinder.ui.delegates.RenderProp
import studio.eyesthetics.authorfinder.viewmodels.base.IViewModelState

abstract class Binding {
    val delegates = mutableMapOf<String, RenderProp<out Any>>()
    var isInflated = false

    open val afterInflated: (() -> Unit)? = null
    fun onFinishInflate() {
        if(!isInflated) {
            afterInflated?.invoke()
            isInflated = true
        }
    }

    fun rebind() {
        delegates.forEach { it.value.bind() }
    }

    abstract fun bind(data : IViewModelState)

    open fun saveUi(outState: Bundle) {}

    open fun restoreUi(savedState: Bundle?) {}
}