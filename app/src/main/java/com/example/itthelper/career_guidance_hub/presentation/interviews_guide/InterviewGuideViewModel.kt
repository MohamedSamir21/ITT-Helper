package com.example.itthelper.career_guidance_hub.presentation.interviews_guide

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.career_guidance_hub.presentation.util.InterviewTips

class InterviewGuideViewModel: ViewModel() {
    private val _state = mutableStateOf(
        InterviewGuideScreenState(
            InterviewTips.tips
        )
    )
    val state: State<InterviewGuideScreenState>
        get() = _state

    fun updateTipItemVisibility(index: Int) {
        val updateTips = state.value.tips.toMutableList()
        updateTips[index] = updateTips[index].copy(showDescription = updateTips[index].showDescription.not())
        _state.value = _state.value.copy(tips = updateTips)
    }
}