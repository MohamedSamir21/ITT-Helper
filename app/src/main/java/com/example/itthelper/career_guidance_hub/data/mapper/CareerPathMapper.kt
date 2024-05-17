package com.example.itthelper.career_guidance_hub.data.mapper

import com.example.itthelper.career_guidance_hub.data.model.CareerPathEntity
import com.example.itthelper.career_guidance_hub.domain.model.CareerPath

fun List<CareerPathEntity>.toDomainCareerPaths(): List<CareerPath> {
    return this.map {
        CareerPath(it.name)
    }
}