package studio.eyesthetics.authorfinder.data.models.responses

import studio.eyesthetics.authorfinder.app.di.modules.NetworkModule.SingleToArray
import studio.eyesthetics.authorfinder.data.models.Title
import studio.eyesthetics.authorfinder.data.models.Works

data class AuthorResponse(
    val authorid: String?,
    val authordisplay: String?,
    val titles: Title?,
    @SingleToArray
    val works: Works?
)