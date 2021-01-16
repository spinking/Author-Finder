package studio.eyesthetics.authorfinder.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import studio.eyesthetics.authorfinder.app.di.modules.NetworkModule.SingleToArray

@JsonClass(generateAdapter = true)
data class Works(
    @SingleToArray
    @Json(name = "works")
    val works: List<String>
)