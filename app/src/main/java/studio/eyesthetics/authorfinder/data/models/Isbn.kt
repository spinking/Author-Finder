package studio.eyesthetics.authorfinder.data.models

import com.squareup.moshi.Json

data class Isbn(
    @Json(name = "@contributortype")
    val contributorType: String,
    @Json(name = "$")
    val contributorId: String
) : DescriptionItem
