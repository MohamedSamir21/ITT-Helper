package com.example.itthelper.career_guidance_hub.data.source.remote.response.voice_flow

data class Slate(
    val id: String,
    val content: List<Content>,
    val messageDelayMilliseconds: Int
)