package com.example.itthelper.career_guidance_hub.presentation.cvs_tips

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.career_guidance_hub.presentation.util.CvsTips

class CvTipsViewModel : ViewModel() {
    private val _state = mutableStateOf(
        CvTipsScreenState(CvsTips.tips)
    )
    val state: State<CvTipsScreenState>
        get() = _state

    fun updateTipItemVisibility(index: Int) {
        val updateTips = _state.value.tips.toMutableList()
        updateTips[index] = updateTips[index].copy(showDescription = updateTips[index].showDescription.not())
        _state.value = _state.value.copy(tips = updateTips)
    }
}