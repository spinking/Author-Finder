package studio.eyesthetics.authorfinder.data.models

data class Author(
    val authorid: String,
    val authordisplay: String,
    val titles: List<Isbn>,
    val works: List<String>
)