package com.example.itthelper.authentication.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("signup")
    suspend fun register(
        @Body request: SignUpRequest
    )

    @POST("signin")
    suspend fun login(
        @Body request: SignInRequest
    ): TokenResponse

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )

}