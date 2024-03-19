package com.example.itthelper.career_guidance_hub.presentation.home

import com.example.itthelper.career_guidance_hub.presentation.util.TabItem

data class HomeScreenState(
    val tabs: List<TabItem>,
    val selectedTabIndex: Int = 0
)
