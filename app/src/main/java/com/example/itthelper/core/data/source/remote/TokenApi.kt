package com.example.itthelper.core.data.source.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("accounts/token/refresh")
    suspend fun refreshAccessToken(
        @Body refresh: RefreshRequest
    ): RefreshResponse
}