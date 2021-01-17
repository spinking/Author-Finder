package studio.eyesthetics.authorfinder.data.repositories

import studio.eyesthetics.authorfinder.data.mappers.AuthorResponseToAuthorMapper
import studio.eyesthetics.authorfinder.data.models.Author
import studio.eyesthetics.authorfinder.data.network.IAuthorApi
import javax.inject.Inject

class AuthorRepository @Inject constructor(
    private val authorApi: IAuthorApi,
    private val mapper: AuthorResponseToAuthorMapper
) : IAuthorRepository {
    override suspend fun getAuthors(query: String): List<Author> {
        return mapper.mapFromListEntity(authorApi.getAuthors(query).author ?: emptyList())
    }

    override suspend fun getAuthorById(authorId: String): Author {
        return mapper.mapFromEntity(authorApi.getAuthorById(authorId).author)
    }
}