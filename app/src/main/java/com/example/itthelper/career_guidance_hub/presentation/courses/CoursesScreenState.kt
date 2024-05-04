package com.example.itthelper.career_guidance_hub.presentation.courses

import androidx.annotation.DrawableRes

data class CoursesScreenState(
    val courses: List<Course>
)

data class Course(
    @DrawableRes
    val thumbnail: Int,
    val courseName: String
)