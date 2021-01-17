package studio.eyesthetics.authorfinder.data.models

import com.sysdata.widget.accordion.Item

data class Description(
    val title: String,
    val items: List<DescriptionItem>
) : Item() {
    override fun getUniqueId(): Int = hashCode()
}