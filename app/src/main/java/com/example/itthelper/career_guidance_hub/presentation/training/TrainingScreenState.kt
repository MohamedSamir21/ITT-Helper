package com.example.itthelper.career_guidance_hub.presentation.training

import androidx.annotation.DrawableRes

data class TrainingScreenState(
    val programs: List<TrainingProgram>,
    val currentPagerIndex: Int = 0
)

data class TrainingProgram(
    @DrawableRes
    val image: Int,
    val name: String,
    val description: String
)
