package com.example.itthelper.career_guidance_hub.presentation.training

import com.example.itthelper.career_guidance_hub.presentation.util.UiText

sealed class TrainingScreenEvent {
    object RetryLoadingPrograms: TrainingScreenEvent()
    object RetryLoadingCourses: TrainingScreenEvent()
    data class Unauthorized(val message: UiText): TrainingScreenEvent()
}