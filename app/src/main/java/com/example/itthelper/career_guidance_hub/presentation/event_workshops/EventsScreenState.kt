package com.example.itthelper.career_guidance_hub.presentation.event_workshops

import androidx.annotation.DrawableRes

data class EventsScreenState(
    val events: List<Event>
)

data class Event(
    @DrawableRes
    val image: Int,
    val name: String,
    val date: String,
    val location: String,
    val deadline: String
)
