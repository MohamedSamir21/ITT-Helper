package com.example.itthelper.career_guidance_hub.presentation.training

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.courses.Course

class TrainingViewModel: ViewModel() {
    private val _state = mutableStateOf(
        TrainingScreenState(
            programs = listOf(
                TrainingProgram(
                    image = R.drawable.intern,
                    name = "Training 1",
                    date = "10:45:00",
                    place = "Alex",
                    company = "Google"
                ),
                TrainingProgram(
                    image = R.drawable.intern,
                    name = "Training 2",
                    date = "10:45:00",
                    place = "Cairo",
                    company = "Meta"
                ),
                TrainingProgram(
                    image = R.drawable.intern,
                    name = "Training 3",
                    date = "10:45:00",
                    place = "Alex",
                    company = "Microsoft"
                )
            ),
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
    val state: State<TrainingScreenState>
        get() = _state
}