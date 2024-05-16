package com.example.itthelper.career_guidance_hub.presentation.event_workshops

import com.example.itthelper.career_guidance_hub.presentation.util.UiText

sealed class EventsScreenEvent {
    object Retry: EventsScreenEvent()
    data class Unauthorized(val message: UiText): EventsScreenEvent()
}