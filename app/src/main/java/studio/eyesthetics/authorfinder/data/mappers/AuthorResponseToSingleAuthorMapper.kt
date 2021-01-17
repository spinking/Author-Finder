package studio.eyesthetics.authorfinder.data.mappers

import studio.eyesthetics.authorfinder.data.models.Description
import studio.eyesthetics.authorfinder.data.models.SingleAuthor
import studio.eyesthetics.authorfinder.data.models.Work
import studio.eyesthetics.authorfinder.data.models.responses.AuthorResponse

class AuthorResponseToSingleAuthorMapper: Mapper<AuthorResponse, SingleAuthor> {
    override fun mapFromEntity(type: AuthorResponse): SingleAuthor {
        val items = mutableListOf<Description>()
        if (type.titles != null && type.titles.isbn.isNotEmpty()) {
            items.add(Description("Titles", type.titles.isbn))
        }
        if (type.works != null && type.works.works.isNotEmpty()) {
            items.add(Description("Works", type.works.works.map { Work(it) }))
        }
        return SingleAuthor(
            authorid = type.authorid ?: "",
            authordisplay = type.authordisplay ?: "",
            items = items
        )
    }

    override fun mapFromListEntity(type: List<AuthorResponse>): List<SingleAuthor> {
        return type.map { mapFromEntity(it) }
    }
}