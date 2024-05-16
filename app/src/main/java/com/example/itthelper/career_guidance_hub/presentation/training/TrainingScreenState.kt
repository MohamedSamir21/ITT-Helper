package com.example.itthelper.career_guidance_hub.presentation.training

import com.example.itthelper.career_guidance_hub.domain.model.Course
import com.example.itthelper.career_guidance_hub.domain.model.TrainingProgram
import com.example.itthelper.career_guidance_hub.presentation.util.UiText

data class TrainingScreenState(
    val programs: List<TrainingProgram>,
    val courses: List<Course>,
    val currentPagerIndex: Int = 0,
    val areProgramsLoading: Boolean = true,
    val areCoursesLoading: Boolean = true,
    val errorOfLoadingPrograms: UiText? = null,
    val errorOfLoadingCourses: UiText? = null,
)
