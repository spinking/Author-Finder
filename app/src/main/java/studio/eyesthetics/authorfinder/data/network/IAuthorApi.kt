package studio.eyesthetics.authorfinder.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import studio.eyesthetics.authorfinder.data.models.responses.AuthorResponseWrapper
import studio.eyesthetics.authorfinder.data.models.responses.AuthorsResponseWrapper

interface IAuthorApi {
    @GET("resources/authors")
    suspend fun getAuthors(
        @Query("lastName") query: String
    ): AuthorsResponseWrapper

    @GET("resources/authors")
    suspend fun getAuthorById(
        @Query("authorid") authorId: String
    ): AuthorResponseWrapper
}