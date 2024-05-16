package com.example.itthelper.core.data.network.interceptor

import com.example.itthelper.core.data.repository.TokenRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

const val HEADER_RETRY_COUNT = "xInternalRetryCount"
const val MAX_RETRY_ATTEMPTS = 21 // To avoid this exception: too many follow-up requests: 21

class RefreshTokenInterceptor(
    private val tokenRepo: TokenRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val newAccessToken: String? = tokenRepo.refreshAccessToken()
        newAccessToken?.let {
            if (retryCount(response) < MAX_RETRY_ATTEMPTS) {
                return response.retryRequest(it)
            }
        }
        return null
    }

    private fun retryCount(response: Response): Int {
        return response.request().header(HEADER_RETRY_COUNT)?.toInt() ?: 1
    }

    private fun Response.retryRequest(newAccessToken: String): Request {
        return this.request().newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            // Add retry count header to the request to keep track of the number of retries
            // by incrementing the value by 1
            .header(HEADER_RETRY_COUNT, "${retryCount(this) + 1}")
            .build()
    }
}