package com.example.itthelper.career_guidance_hub.presentation.courses

import com.example.itthelper.career_guidance_hub.presentation.util.UiText


sealed class CoursesScreenEvent {
    object Retry : CoursesScreenEvent()
    data class Unauthorized(val message: UiText):CoursesScreenEvent()
}