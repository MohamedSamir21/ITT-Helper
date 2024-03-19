package com.example.itthelper.career_guidance_hub.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Courses: Screen(route = "courses")
    object Training: Screen(route = "training")
}