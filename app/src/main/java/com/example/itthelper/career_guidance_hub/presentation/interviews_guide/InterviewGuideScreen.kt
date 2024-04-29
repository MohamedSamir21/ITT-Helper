package com.example.itthelper.career_guidance_hub.presentation.interviews_guide

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itthelper.career_guidance_hub.presentation.components.TipsComponent
import com.example.itthelper.career_guidance_hub.presentation.util.InterviewTips
import com.example.itthelper.career_guidance_hub.presentation.util.TipItem

@Composable
fun InterviewGuideScreen(
    tips: List<TipItem>,
    onClick: (Int) -> Unit
) {
    TipsComponent(
        modifier = Modifier.padding(5.dp),
        tips = tips
    ) { index ->
        onClick(index)
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun InterviewGuidePreview() {
    var state by remember {
        mutableStateOf(
            InterviewGuideScreenState(InterviewTips.tips)
        )
    }

    InterviewGuideScreen(tips = state.tips) { index ->
        val updatedTips = state.tips.toMutableList()
        updatedTips[index] =
            updatedTips[index].copy(showDescription = updatedTips[index].showDescription.not())
        state = state.copy(tips = updatedTips)
    }
}