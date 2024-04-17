package com.example.itthelper.authentication.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("accounts/register")
    suspend fun register(
        @Body request: RegisterRequest
    )

    @POST("accounts/login_user")
    suspend fun login(
        @Body request: LoginRequest
    ): TokenResponse

}