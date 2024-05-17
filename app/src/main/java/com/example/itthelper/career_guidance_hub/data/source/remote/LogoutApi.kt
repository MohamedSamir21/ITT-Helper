package com.example.itthelper.career_guidance_hub.data.source.remote

import com.example.itthelper.core.data.source.remote.RefreshRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LogoutApi {
    @POST("accounts/logout_user")
    suspend fun logout(
        @Body refresh: RefreshRequest
    ): Response
}

data class Response(val response: String)