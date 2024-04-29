package com.example.itthelper.career_guidance_hub.presentation.util

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TipItem(
    @StringRes
    val titleId: Int,
    @StringRes
    val descriptionId: Int,
    val icon: ImageVector,
    var showDescription: Boolean = false
)



