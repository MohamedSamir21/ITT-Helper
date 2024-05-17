package com.example.itthelper.career_guidance_hub.data.model

import com.squareup.moshi.Json

data class EventEntity(
    @field:Json(name = "event")
    val name: String,
    @field:Json(name = "EventTime")
    val time: String,
    @field:Json(name = "Eventplace")
    val place: String
)
