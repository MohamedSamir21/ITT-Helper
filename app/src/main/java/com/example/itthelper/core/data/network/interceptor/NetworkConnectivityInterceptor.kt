package com.example.itthelper.core.data.network.interceptor

import com.example.itthelper.core.data.network.exception.NoConnectivityException
import com.example.itthelper.core.data.repository.NetworkConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectivityInterceptor(
    private val connectivityManager: NetworkConnectivityManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (connectivityManager.isConnected().not()) {
            throw NoConnectivityException()
        }
        val request = chain.request().newBuilder().build()
        return chain.proceed(request)
    }
}