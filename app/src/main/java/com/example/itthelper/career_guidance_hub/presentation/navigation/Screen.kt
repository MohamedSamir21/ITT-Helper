package com.example.itthelper.career_guidance_hub.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Courses: Screen(route = "courses")
    object Training: Screen(route = "training")
    object Profile: Screen(route = "profile")
    object ContactUs: Screen(route = "contact_us")
    object Feedback: Screen(route = "feedback")
    object ChatBot: Screen(route = "Chat_bot")
}