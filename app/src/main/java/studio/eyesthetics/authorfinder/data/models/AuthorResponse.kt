package studio.eyesthetics.authorfinder.data.models

import studio.eyesthetics.authorfinder.app.di.modules.NetworkModule.SingleToArray

data class AuthorResponse(
    val authorid: String?,
    val authordisplay: String?,
    val titles: Title?,
    @SingleToArray
    val works: Works?
)