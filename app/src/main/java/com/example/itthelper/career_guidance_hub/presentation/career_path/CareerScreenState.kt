package com.example.itthelper.career_guidance_hub.presentation.career_path

import com.example.itthelper.career_guidance_hub.domain.model.CareerPath
import com.example.itthelper.career_guidance_hub.presentation.util.UiText

data class CareerScreenState(
    val paths: List<CareerPath>,
    val careerBannerItems: List<CareerBannerItem>,
    val error: UiText? = null,
    val isLoading: Boolean = true
)