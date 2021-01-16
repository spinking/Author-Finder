package studio.eyesthetics.authorfinder.app.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import studio.eyesthetics.authorfinder.BuildConfig
import studio.eyesthetics.authorfinder.data.network.IAuthorApi
import studio.eyesthetics.authorfinder.data.network.interceptors.ErrorStatusInterceptor
import studio.eyesthetics.authorfinder.data.network.interceptors.NetworkStatusInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class CoroutineScopeIO

    @Provides
    @Singleton
    fun provideJsonConverter(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Provides
    fun provideNetworkStatusInterceptor() = NetworkStatusInterceptor()

    @Provides
    fun provideErrorStatusInterceptor(
        moshi: Moshi
    ) = ErrorStatusInterceptor(moshi)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkStatusInterceptor: NetworkStatusInterceptor,
        errorStatusInterceptor: ErrorStatusInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(errorStatusInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideEventApi(retrofit: Retrofit): IAuthorApi =
        retrofit.create(IAuthorApi::class.java)
}