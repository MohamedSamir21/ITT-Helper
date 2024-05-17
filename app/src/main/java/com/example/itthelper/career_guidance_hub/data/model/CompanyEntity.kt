package com.example.itthelper.career_guidance_hub.data.model

import com.squareup.moshi.Json

data class CompanyEntity(
    val id: Int,
    @field:Json(name = "CompanyName")
    val name: String
)
