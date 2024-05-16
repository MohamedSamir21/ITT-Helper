package com.example.itthelper.career_guidance_hub.data.mapper

import com.example.itthelper.career_guidance_hub.data.model.CourseEntity
import com.example.itthelper.career_guidance_hub.domain.model.Course

fun List<CourseEntity>.toDomainCourses(): List<Course> {
    return this.map { Course(it.name) }
}