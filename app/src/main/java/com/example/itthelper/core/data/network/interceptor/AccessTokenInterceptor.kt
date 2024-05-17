package com.example.itthelper.core.data.network.interceptor

import com.example.itthelper.core.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor(
    private val tokenRepo: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val accessToken = tokenRepo.readAccessToken()
        val requestWithAccessToken = originalRequest.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(requestWithAccessToken)
    }
}