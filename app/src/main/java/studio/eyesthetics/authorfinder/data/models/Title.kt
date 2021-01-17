package studio.eyesthetics.authorfinder.data.models

import com.squareup.moshi.JsonClass
import studio.eyesthetics.authorfinder.app.di.modules.NetworkModule.SingleToArray

@JsonClass(generateAdapter = true)
data class Title(
    @SingleToArray
    val isbn: List<Isbn>
)