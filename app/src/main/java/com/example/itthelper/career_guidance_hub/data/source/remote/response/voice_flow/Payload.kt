package com.example.itthelper.career_guidance_hub.data.source.remote.response.voice_flow

data class Payload(
    val path: String? = null,
    val slate: Slate? = null,
    val message: String? = null,
    val delay: Int? = null,
    val ai: Boolean? = null
)