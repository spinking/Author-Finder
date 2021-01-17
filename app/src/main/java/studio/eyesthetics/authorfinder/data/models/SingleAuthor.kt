package studio.eyesthetics.authorfinder.data.models

data class SingleAuthor(
    val authorid: String,
    val authordisplay: String,
    val items: List<Description>
)