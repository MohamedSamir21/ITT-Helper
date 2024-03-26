package com.example.itthelper.career_guidance_hub.presentation.training

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.R

class TrainingViewModel: ViewModel() {
    private val _state = mutableStateOf(
        TrainingScreenState(
            programs = listOf(
                TrainingProgram(
                    image = R.drawable.educational_resources,
                    name = "Web Development Bootcamp",
                    description = "Join our intensive web development bootcamp"
                ),
                TrainingProgram(
                    image = R.drawable.educational_resources,
                    name = "Data Science Workshop",
                    description = "Explore the world of data science"
                ),
                TrainingProgram(
                    image = R.drawable.educational_resources,
                    name = "CyberSecurity Training",
                    description = "Enhance your cybersecurity skills"
                )
            )
        )
    )
    val state: State<TrainingScreenState>
        get() = _state
}