package com.example.itthelper.career_guidance_hub.data.model

import com.squareup.moshi.Json

data class CourseEntity(
    @field:Json(name = "CourseName")
    val name: String
)
