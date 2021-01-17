package studio.eyesthetics.authorfinder.app.di.modules

import dagger.Module
import dagger.Provides
import studio.eyesthetics.authorfinder.data.mappers.AuthorResponseToAuthorMapper
import studio.eyesthetics.authorfinder.data.mappers.AuthorResponseToSingleAuthorMapper

@Module
class MapperModule {
    @Provides
    fun provideAuthorMapper(): AuthorResponseToAuthorMapper = AuthorResponseToAuthorMapper()

    @Provides
    fun provideSingleAuthorMapper(): AuthorResponseToSingleAuthorMapper = AuthorResponseToSingleAuthorMapper()
}