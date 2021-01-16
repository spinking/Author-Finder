package studio.eyesthetics.authorfinder.data.network.interceptors

import com.google.gson.JsonParseException
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import studio.eyesthetics.authorfinder.data.network.errors.ApiError
import studio.eyesthetics.authorfinder.data.network.errors.Error
import studio.eyesthetics.authorfinder.data.network.errors.ErrorBody
import javax.inject.Inject

class ErrorStatusInterceptor @Inject constructor(
    private val moshi: Moshi
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val res = chain.proceed(chain.request())

        if (res.isSuccessful) return res

        var error = try {
            moshi.adapter(Error::class.java).fromJson(res.body!!.string())?.error
        } catch (e: JsonParseException) {
            ErrorBody(
                "Undefiled",
                e.message ?: "Undefined"
            )
        }

        if(error == null) error = ErrorBody(
            "Undefiled",
            "Undefined"
        )

        when (res.code) {
            400 -> throw ApiError.BadRequest(error.message)
            404 -> throw ApiError.NotFound(error.message)
            500 -> throw ApiError.InternalServerError(error.message)
            else -> throw ApiError.UnknownError(error.message)
        }
    }
}