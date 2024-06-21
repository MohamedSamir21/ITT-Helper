package com.example.itthelper.career_guidance_hub.data.source.remote.api

import com.example.itthelper.career_guidance_hub.data.source.remote.request.voice_flow.VoiceFlowRequest
import com.example.itthelper.career_guidance_hub.data.source.remote.response.voice_flow.VoiceFlowResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface VoiceFlowApi {
    @POST("state/6663576dc6f7b8ef04e32eb3/user/1/interact")
    suspend fun sendMessage(
        @Header("Authorization") apiKey: String,
        @Body request: VoiceFlowRequest
    ): List<VoiceFlowResponse>
}

