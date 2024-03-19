package com.example.itthelper.career_guidance_hub.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.itthelper.career_guidance_hub.presentation.home.HomeScreen
import com.example.itthelper.career_guidance_hub.presentation.home.HomeViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.Home.route
        ) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel
            )
        }
        composable(
            route = Screen.Courses.route
        ) {

        }
        composable(
            route = Screen.Training.route
        ) {

        }
    }
}