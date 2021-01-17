package studio.eyesthetics.authorfinder.data.models.responses

import com.squareup.moshi.JsonClass
import studio.eyesthetics.authorfinder.app.di.modules.NetworkModule.SingleToArray

@JsonClass(generateAdapter = true)
class AuthorsResponseWrapper(
    @SingleToArray
    val author: List<AuthorResponse>?
)