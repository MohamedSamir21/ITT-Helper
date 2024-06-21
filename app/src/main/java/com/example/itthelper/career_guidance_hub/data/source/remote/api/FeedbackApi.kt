package com.example.itthelper.career_guidance_hub.data.source.remote.api

import com.example.itthelper.career_guidance_hub.data.model.FeedbackEntity
import com.example.itthelper.core.data.source.remote.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FeedbackApi {
    @POST("api/feedback")
    suspend fun sendFeedback(@Body feedbackEntity: FeedbackEntity): Response
}
