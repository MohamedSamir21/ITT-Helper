package com.example.itthelper.career_guidance_hub.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.career_guidance_hub.presentation.util.TabContent
import com.example.itthelper.career_guidance_hub.presentation.util.TabItem

class HomeViewModel: ViewModel() {
    private val _state = mutableStateOf(
        HomeScreenState(
            tabs = listOf(
                TabItem("Career path", TabContent.CareerPath),
                TabItem("Employment Market", TabContent.EmploymentMarket),
                TabItem("Events & Workshop", TabContent.EventsWorkshops),
                TabItem("Cvs & Tips", TabContent.CvsTips),
                TabItem("Guide For interviews", TabContent.GuideForInterviews)
            )
        )
    )
    val state: State<HomeScreenState>
        get() = _state

    fun updateSelectedTabIndex(newIndex: Int) {
        _state.value = state.value.copy(selectedTabIndex = newIndex)
    }
}