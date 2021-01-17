package studio.eyesthetics.authorfinder.data.mappers

import studio.eyesthetics.authorfinder.data.models.Author
import studio.eyesthetics.authorfinder.data.models.responses.AuthorResponse

class AuthorResponseToAuthorMapper : Mapper<AuthorResponse, Author> {
    override fun mapFromEntity(type: AuthorResponse): Author {
        return Author(
            authorid = type.authorid ?: "",
            authordisplay = type.authordisplay ?: ""
        )
    }

    override fun mapFromListEntity(type: List<AuthorResponse>): List<Author> {
        return type.map { mapFromEntity(it) }
    }
}