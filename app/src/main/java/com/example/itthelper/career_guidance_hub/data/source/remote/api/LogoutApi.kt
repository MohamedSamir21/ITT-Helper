package com.example.itthelper.career_guidance_hub.data.source.remote.api

import com.example.itthelper.core.data.source.remote.RefreshRequest
import com.example.itthelper.core.data.source.remote.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogoutApi {
    @POST("accounts/logout_user")
    suspend fun logout(
        @Body refresh: RefreshRequest
    ): Response
}