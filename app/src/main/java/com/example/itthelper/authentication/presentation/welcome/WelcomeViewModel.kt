package com.example.itthelper.authentication.presentation.welcome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.authentication.presentation.util.OnBoardingPage

class WelcomeViewModel : ViewModel() {
    private val _state = mutableStateOf(
        WelcomeScreenState(
            onBoardingPages = listOf(
                OnBoardingPage.First,
                OnBoardingPage.Second,
                OnBoardingPage.Third
            )
        )
    )
    val state: State<WelcomeScreenState>
        get() = _state

    fun updateCurrentPageIndex(index: Int){
        _state.value = _state.value.copy(currentPageIndex = index)
    }
}