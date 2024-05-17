package com.example.itthelper.career_guidance_hub.presentation.profile

import com.example.itthelper.career_guidance_hub.presentation.util.UiText

sealed class ProfileScreenEvent {
    sealed class Logout : ProfileScreenEvent() {
        data class Success(val message: UiText) : Logout()
        data class Error(val message: UiText) : Logout()
    }
}