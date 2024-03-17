package com.example.itthelper.career_guidance_hub.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var selectedNavigationItemIndex by mutableStateOf(0)
        private set
    var selectedTabIndex by mutableStateOf(0)
        private set

    fun updateSelectedIndex(newSelectedIndex: Int){
        selectedNavigationItemIndex = newSelectedIndex
    }

    fun updateSelectedTabIndex(newSelectedTabIndex: Int){
        selectedTabIndex = newSelectedTabIndex
    }
}