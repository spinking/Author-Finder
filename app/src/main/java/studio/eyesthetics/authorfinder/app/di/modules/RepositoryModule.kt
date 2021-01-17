package studio.eyesthetics.authorfinder.app.di.modules

import dagger.Module
import dagger.Provides
import studio.eyesthetics.authorfinder.data.mappers.AuthorResponseToAuthorMapper
import studio.eyesthetics.authorfinder.data.mappers.AuthorResponseToSingleAuthorMapper
import studio.eyesthetics.authorfinder.data.network.IAuthorApi
import studio.eyesthetics.authorfinder.data.repositories.AuthorRepository
import studio.eyesthetics.authorfinder.data.repositories.IAuthorRepository

@Module
class RepositoryModule {
    @Provides
    fun provideAuthorRepository(
        authorApi: IAuthorApi,
        mapper: AuthorResponseToAuthorMapper,
        singleMapper: AuthorResponseToSingleAuthorMapper
    ): IAuthorRepository = AuthorRepository(authorApi, mapper, singleMapper)
}