package studio.eyesthetics.authorfinder.app.di.modules

import com.squareup.moshi.JsonQualifier
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
import studio.eyesthetics.authorfinder.data.SingleToArrayAdapter
import studio.eyesthetics.authorfinder.data.network.IAuthorApi
import studio.eyesthetics.authorfinder.data.network.interceptors.ErrorStatusInterceptor
import studio.eyesthetics.authorfinder.data.network.interceptors.HeaderInterceptor
import studio.eyesthetics.authorfinder.data.network.interceptors.NetworkStatusInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class CoroutineScopeIO

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @JsonQualifier
    annotation class SingleToArray

    @Provides
    @Singleton
    fun provideJsonConverter(): Moshi =
        Moshi.Builder()
            .add(SingleToArrayAdapter.INSTANCE)
            .add(KotlinJsonAdapterFactory())
            .build()

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Provides
    fun provideNetworkStatusInterceptor() = NetworkStatusInterceptor()

    @Provides
    fun provideHeaderInterceptor() = HeaderInterceptor()

    @Provides
    fun provideErrorStatusInterceptor(
        moshi: Moshi
    ) = ErrorStatusInterceptor(moshi)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkStatusInterceptor: NetworkStatusInterceptor,
        errorStatusInterceptor: ErrorStatusInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(errorStatusInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    fun provideAuthorApi(retrofit: Retrofit): IAuthorApi =
        retrofit.create(IAuthorApi::class.java)
}