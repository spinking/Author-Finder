package studio.eyesthetics.authorfinder.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import studio.eyesthetics.authorfinder.data.network.errors.ContentTypeError
import java.lang.IllegalArgumentException

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        try {
            request = request.newBuilder().addHeader("Accept", "application/json").build()
        } catch (e: IllegalArgumentException) {
            throw ContentTypeError(e.message ?: "")
        }
        return chain.proceed(request)
    }
}