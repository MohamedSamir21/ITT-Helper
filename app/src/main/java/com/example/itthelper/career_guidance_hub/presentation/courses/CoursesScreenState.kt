package com.example.itthelper.career_guidance_hub.presentation.courses

import com.example.itthelper.career_guidance_hub.domain.model.Course
import com.example.itthelper.career_guidance_hub.presentation.util.UiText

data class CoursesScreenState(
    val courses: List<Course>,
    val error: UiText? = null,
    val isLoading: Boolean = true,
)
