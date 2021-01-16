package studio.eyesthetics.authorfinder.app.di.modules

import dagger.Module
import dagger.Provides
import studio.eyesthetics.authorfinder.data.mappers.AuthorResponseToAuthorMapper

@Module
class MapperModule {
    @Provides
    fun provideAuthorMapper(): AuthorResponseToAuthorMapper = AuthorResponseToAuthorMapper()
}