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

    fun updateCurrentPageIndex(currentPageIndex: Int) {
        val oldPageIndex = state.value.currentPageIndex
        if (currentPageIndex > oldPageIndex)
            increaseCurrentPageIndex(currentPageIndex)
        else if (currentPageIndex < oldPageIndex)
            decreaseCurrentPageIndex(currentPageIndex)
    }

    private fun increaseCurrentPageIndex(currentPageIndex: Int) {
        val state = state.value
        _state.value = state.copy(currentPageIndex = currentPageIndex)
        if (currentPageIndex == 2)
            toggleLastPageState(state.isLastPage)
    }

    private fun decreaseCurrentPageIndex(currentPageIndex: Int) {
        val state = state.value
        _state.value = state.copy(currentPageIndex = currentPageIndex)
        if (currentPageIndex == 1)
            toggleLastPageState(state.isLastPage)
    }

    private fun toggleLastPageState(oldValue: Boolean) {
        _state.value = state.value.copy(isLastPage = oldValue.not())
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