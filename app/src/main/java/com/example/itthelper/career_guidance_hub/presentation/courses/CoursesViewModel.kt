package com.example.itthelper.career_guidance_hub.presentation.courses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.R

class CoursesViewModel : ViewModel() {

    private val _state = mutableStateOf(
        CoursesScreenState(
            courses = listOf(
                Course(
                    thumbnail = R.drawable.play,
                    courseName = "Course"
                ),
                Course(
                    thumbnail = R.drawable.play,
                    courseName = "Course"
                ),
                Course(
                    thumbnail = R.drawable.play,
                    courseName = "Course"
                )
            )
        )
    )

    val state: State<CoursesScreenState>
        get() = _state
}