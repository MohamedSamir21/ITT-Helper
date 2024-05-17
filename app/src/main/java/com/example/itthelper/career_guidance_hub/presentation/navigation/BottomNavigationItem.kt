package com.example.itthelper.career_guidance_hub.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.itthelper.R

data class BottomNavigationItem(
    @StringRes
    val titleResId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        titleResId = R.string.home,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = Screen.Home.route
    ),
    BottomNavigationItem(
        titleResId = R.string.courses,
        selectedIcon = Icons.Default.PlayArrow,
        unselectedIcon = Icons.Outlined.PlayArrow,
        route = Screen.Courses.route
    ),
    BottomNavigationItem(
        titleResId = R.string.training,
        selectedIcon = Icons.Default.Face,
        unselectedIcon = Icons.Outlined.Face,
        route = Screen.Training.route
    ),
)
