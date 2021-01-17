package studio.eyesthetics.authorfinder.ui.base

import android.view.MenuItem

data class MenuItemHolder(
    val title: String,
    val menuId: Int,
    val icon: Int? = null,
    val actionViewLayout: Int? = null,
    val clickListener: ((MenuItem) -> Unit)? = null
)