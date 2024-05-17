package com.example.itthelper.career_guidance_hub.data.model

import com.squareup.moshi.Json

data class TrainingProgramEntity(
    @field:Json(name = "TrainingName")
    val name: String,
    @field:Json(name = "TrainingTime")
    val time: String,
    @field:Json(name = "TrainingPlace")
    val place: String,
    @field:Json(name = "TrainingCompany")
    val company: List<Int>
)
