package studio.eyesthetics.authorfinder.data.repositories

import studio.eyesthetics.authorfinder.data.models.Author
import studio.eyesthetics.authorfinder.data.models.SingleAuthor

interface IAuthorRepository {
    suspend fun getAuthors(query: String): List<Author>
    suspend fun getAuthorById(authorId: String): SingleAuthor
}