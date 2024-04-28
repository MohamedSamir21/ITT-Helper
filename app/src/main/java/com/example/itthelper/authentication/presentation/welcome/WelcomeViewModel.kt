package com.example.itthelper.authentication.presentation.welcome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.authentication.domain.usecase.preferences.SaveWelcomeDoneUseCase
import com.example.itthelper.authentication.presentation.util.OnBoardingPage
import com.example.itthelper.core.ui.theme.primaryDark
import com.example.itthelper.core.ui.theme.primaryLight
import com.example.itthelper.core.ui.theme.secondaryDark
import com.example.itthelper.core.ui.theme.secondaryLight
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val saveWelcomeDoneUseCase: SaveWelcomeDoneUseCase
) : ViewModel() {
    private val _state = mutableStateOf(
        WelcomeScreenState(
            onBoardingPages = listOf(
                OnBoardingPage.First,
                OnBoardingPage.Second,
                OnBoardingPage.Third
            ),
            indicatorCirclesColors = listOf(
                IndicatorCircleColor(primaryLight),
                IndicatorCircleColor(secondaryLight),
                IndicatorCircleColor(secondaryLight)
            )
        )
    )
    val state: State<WelcomeScreenState>
        get() = _state

    init {
        if (state.value.isSystemInDarkTheme) {
            _state.value = _state.value.copy(
                indicatorCirclesColors = listOf(
                    IndicatorCircleColor(primaryDark),
                    IndicatorCircleColor(secondaryDark),
                    IndicatorCircleColor(secondaryDark)
                )
            )
        }
    }

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
                if (areTheSameIndexes(colorItemIndex, currentPageIndex) && state.value.isSystemInDarkTheme)
                    colorItem.copy(color = primaryDark)
                else if (areTheSameIndexes(colorItemIndex, currentPageIndex) && state.value.isSystemInDarkTheme.not())
                    colorItem.copy(color = primaryLight)
                else if (state.value.isSystemInDarkTheme)
                    colorItem.copy(color = secondaryDark)
                else
                    colorItem.copy(color = secondaryLight)
            }
        )
    }

    private fun areTheSameIndexes(firstIndex: Int, secondIndex: Int) = firstIndex == secondIndex

    suspend fun saveOnWelcomeDone(done: Boolean) {
        saveWelcomeDoneUseCase(done)
    }

    fun setSystemThemeState(isDark: Boolean) {
        _state.value = state.value.copy(isSystemInDarkTheme = isDark)
    }

}