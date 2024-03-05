package com.example.itthelper.authentication.presentation.welcome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.itthelper.authentication.presentation.util.OnBoardingPage

class WelcomeViewModel : ViewModel() {
    private val _state = mutableStateOf(
        WelcomeScreenState(
            onBoardingPages = listOf(
                OnBoardingPage.First,
                OnBoardingPage.Second,
                OnBoardingPage.Third
            ),
            indicatorCirclesColors = listOf(
                IndicatorCircleColor(Color.Green),
                IndicatorCircleColor(Color.Gray),
                IndicatorCircleColor(Color.Gray)
            )
        )
    )
    val state: State<WelcomeScreenState>
        get() = _state

    fun updateCurrentPageIndex(index: Int) {
        val oldPageIndex = _state.value.currentPageIndex
        if (index > oldPageIndex)
            increaseCurrentPageIndex(index)
        else
            decreaseCurrentPageIndex(index)
    }

    fun increaseCurrentPageIndex(index: Int) {
        if (index < 3)
            _state.value = _state.value.copy(currentPageIndex = index + 1)
    }

    private fun decreaseCurrentPageIndex(index: Int) {
        if (index > 0)
            _state.value = _state.value.copy(currentPageIndex = index - 1)
    }

    fun updateIndicatorCirclesColors(currentPageIndex: Int) {
        _state.value = _state.value.copy(
            indicatorCirclesColors = _state.value.indicatorCirclesColors.mapIndexed { colorItemIndex, colorItem ->
                if (colorItemIndex == currentPageIndex)
                    colorItem.copy(color = Color.Green)
                else
                    colorItem.copy(color = Color.Gray)
            }
        )
    }

}