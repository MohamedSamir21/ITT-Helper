package com.example.itthelper.career_guidance_hub.data.model

import com.squareup.moshi.Json

data class FeedbackEntity(
    @field:Json(name = "text")
    val message: String
)