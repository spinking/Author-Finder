package studio.eyesthetics.authorfinder.data.mappers

import studio.eyesthetics.authorfinder.data.models.Author
import studio.eyesthetics.authorfinder.data.models.AuthorResponse

class AuthorResponseToAuthorMapper : Mapper<AuthorResponse, Author> {
    override fun mapFromEntity(type: AuthorResponse): Author {
        return Author(
            authorid = type.authorid ?: "",
            authordisplay = type.authordisplay ?: "",
            titles = type.titles?.isbn ?: emptyList(),
            works = type.works?.works ?: emptyList(),
        )
    }

    override fun mapFromListEntity(type: List<AuthorResponse>): List<Author> {
        return type.map { mapFromEntity(it) }
    }
}