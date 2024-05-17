package com.example.itthelper.career_guidance_hub.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.career_guidance_hub.presentation.util.Tabs

class HomeViewModel : ViewModel() {
    private val _state = mutableStateOf(
        HomeScreenState(
            tabs = Tabs.tabs
        )
    )
    val state: State<HomeScreenState>
        get() = _state

    fun updateSelectedTabIndex(newIndex: Int) {
        _state.value = state.value.copy(selectedTabIndex = newIndex)
    }
}