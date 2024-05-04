package com.example.itthelper.career_guidance_hub.presentation.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PageSize
import androidx.compose.ui.unit.Density

@ExperimentalFoundationApi
val SmallPageSize = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return (availableSpace - 2 * pageSpacing) / 2
    }
}

@ExperimentalFoundationApi
val LargePageSize = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return ((availableSpace - 50) - 2 * pageSpacing)
    }
}

