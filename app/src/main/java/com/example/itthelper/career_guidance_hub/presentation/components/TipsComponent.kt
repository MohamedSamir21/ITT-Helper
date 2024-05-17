package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.util.CvsTips
import com.example.itthelper.career_guidance_hub.presentation.util.TipItem

@Composable
fun TipsComponent(
    modifier: Modifier = Modifier,
    tips: List<TipItem>,
    tipsHeadline: String,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TipHeadline(
            modifier = Modifier.padding(vertical = 10.dp),
            icon = Icons.Default.Lightbulb,
            iconTint = MaterialTheme.colorScheme.onTertiaryContainer,
            text = tipsHeadline,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Card(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            tips.forEachIndexed { index, tipItem ->
                TipItem(tipItem = tipItem) {
                    onClick(index)
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TipsComponentPreview() {
    var state by remember {
        mutableStateOf(CvsTips.tips)
    }

    TipsComponent(
        tips = state,
        tipsHeadline = stringResource(R.string.there_are_some_guides_for_interviews)
    ) { index ->
        val updatedTips = state.toMutableList()
        updatedTips[index] =
            updatedTips[index].copy(showDescription = state[index].showDescription.not())
        state = updatedTips
    }
}