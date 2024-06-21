package com.example.itthelper.career_guidance_hub.data.source.remote.api

import com.example.itthelper.career_guidance_hub.data.model.ContactUsEntity
import com.example.itthelper.core.data.source.remote.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactUsApi {
    @POST("api/contact-us")
    suspend fun sendMessage(@Body contactUs: ContactUsEntity): Response
}

