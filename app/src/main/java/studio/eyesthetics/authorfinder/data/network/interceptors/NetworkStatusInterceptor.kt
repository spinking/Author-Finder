package studio.eyesthetics.authorfinder.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import studio.eyesthetics.authorfinder.data.network.NetworkMonitor
import studio.eyesthetics.authorfinder.data.network.errors.NoNetworkError

class NetworkStatusInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkMonitor.isConnected) throw NoNetworkError()

        return chain.proceed(chain.request())
    }
}