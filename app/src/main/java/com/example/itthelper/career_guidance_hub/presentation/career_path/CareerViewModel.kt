package com.example.itthelper.career_guidance_hub.presentation.career_path

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CareerViewModel : ViewModel() {
    private val _state = mutableStateOf(
        CareerScreenState(
            paths = listOf(
                "path 1",
                "path 2",
                "path 3"
            ),
            careerBannerItems = CareerBanner.items
        )
    )

    val state: State<CareerScreenState>
        get() = _state
}