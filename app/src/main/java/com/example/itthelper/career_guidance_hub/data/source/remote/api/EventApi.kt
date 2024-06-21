package com.example.itthelper.career_guidance_hub.data.source.remote.api

import com.example.itthelper.career_guidance_hub.data.model.EventEntity
import retrofit2.http.GET

interface EventApi {
    @GET("api/events/")
    suspend fun getEvents(): List<EventEntity>
}