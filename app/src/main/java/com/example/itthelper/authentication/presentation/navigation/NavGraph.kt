package com.example.itthelper.authentication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.itthelper.authentication.presentation.auth.AuthScreen
import com.example.itthelper.authentication.presentation.login.LoginScreen
import com.example.itthelper.authentication.presentation.login.LoginViewModel
import com.example.itthelper.authentication.presentation.register.RegisterScreen
import com.example.itthelper.authentication.presentation.register.RegisterViewModel
import com.example.itthelper.authentication.presentation.welcome.WelcomeScreen
import com.example.itthelper.authentication.presentation.welcome.WelcomeViewModel

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
            val viewModel: WelcomeViewModel = hiltViewModel()
            WelcomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Screen.AUTH.route) {
            AuthScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.REGISTER.route
        ) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            RegisterScreen(
                navController = navController,
                viewModel = registerViewModel
            )
        }
        composable(
            route = Screen.LOGIN.route
        ) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }
    }
}