package com.example.itthelper.career_guidance_hub.presentation.training

import androidx.annotation.DrawableRes
import com.example.itthelper.career_guidance_hub.presentation.courses.Course

data class TrainingScreenState(
    val programs: List<TrainingProgram>,
    val courses: List<Course>,
    val currentPagerIndex: Int = 0
)

data class TrainingProgram(
    @DrawableRes
    val image: Int,
    val name: String,
    val date: String,
    val place: String,
    val company: String
)
