package com.example.itthelper.authentication.presentation.welcome

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import com.example.itthelper.authentication.presentation.util.OnBoardingPage

data class WelcomeScreenState(
    val onBoardingPages: List<OnBoardingPage>,
    val currentPageIndex: Int = 0,
    val indicatorCirclesColors: List<IndicatorCircleColor>
)

data class IndicatorCircleColor(
    @ColorRes
    val color: Color
)