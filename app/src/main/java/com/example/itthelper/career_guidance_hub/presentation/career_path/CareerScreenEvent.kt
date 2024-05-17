package com.example.itthelper.career_guidance_hub.presentation.career_path

import com.example.itthelper.career_guidance_hub.presentation.util.UiText

sealed class CareerScreenEvent {
    object Retry: CareerScreenEvent()
    data class Unauthorized(val message: UiText): CareerScreenEvent()
}