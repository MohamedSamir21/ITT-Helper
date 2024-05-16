package com.example.itthelper.career_guidance_hub.presentation.event_workshops

import com.example.itthelper.career_guidance_hub.domain.model.Event
import com.example.itthelper.career_guidance_hub.presentation.util.UiText


data class EventsScreenState(
    val events: List<Event>,
    val error: UiText? = null,
    val isLoading: Boolean = true
)

