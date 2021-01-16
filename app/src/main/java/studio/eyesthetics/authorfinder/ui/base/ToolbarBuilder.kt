package studio.eyesthetics.authorfinder.ui.base

import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import studio.eyesthetics.authorfinder.R

class ToolbarBuilder {
    var title: String? = null
    var isBackButtonVisible: Boolean = true
    val items: MutableList<MenuItemHolder> = mutableListOf()

    fun setTitle(title: String): ToolbarBuilder {
        this.title = title
        return this
    }

    fun setBackButtonVisible(isVisible: Boolean): ToolbarBuilder {
        this.isBackButtonVisible = isVisible
        return this
    }

    fun addMenuItem(item: MenuItemHolder): ToolbarBuilder {
        this.items.add(item)
        return this
    }

    fun invalidate(): ToolbarBuilder {
        this.title = null
        this.isBackButtonVisible = true
        this.items.clear()
        return this
    }

    fun prepare(prepareFn: (ToolbarBuilder.() -> Unit)?): ToolbarBuilder {
        prepareFn?.invoke(this)
        return this
    }

    fun build(context: FragmentActivity) {

        with(context.toolbar) {
            if (this@ToolbarBuilder.title != null) {
                this.title = this@ToolbarBuilder.title
            } else {
                this.title = ""
            }

            if (this@ToolbarBuilder.isBackButtonVisible.not()) {
                this.navigationIcon = null
            } else {
                this.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null)
            }
        }
    }
}