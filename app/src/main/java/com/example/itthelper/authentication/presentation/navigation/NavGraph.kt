package com.example.itthelper.authentication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.itthelper.authentication.presentation.welcome.WelcomeScreen

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
            route = Screen.WELCOME.route
        ) {
            WelcomeScreen()
        }
        composable(route = Screen.AUTH.route){

        }
        composable(
            route = Screen.REGISTER.route
        ){

        }
        composable(
            route = Screen.LOGIN.route
        ){

        }
    }
}