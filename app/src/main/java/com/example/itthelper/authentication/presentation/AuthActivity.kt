package com.example.itthelper.authentication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.authentication.presentation.navigation.Screen
import com.example.itthelper.authentication.presentation.navigation.SetupNavGraph
import com.example.itthelper.ui.theme.ITTHelperTheme

class AuthActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                AuthFeature()
            }
        }
    }
}

@Composable
fun AuthFeature() {
    val navController = rememberNavController()
    SetupNavGraph(
        navController = navController,
        startDestination = Screen.WELCOME.route
    )
}