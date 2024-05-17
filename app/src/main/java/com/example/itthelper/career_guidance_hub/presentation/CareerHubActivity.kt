package com.example.itthelper.career_guidance_hub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.career_guidance_hub.presentation.components.CareerHubScaffold
import com.example.itthelper.career_guidance_hub.presentation.navigation.bottomNavigationItems
import com.example.itthelper.core.ui.theme.ITTHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CareerHubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                val viewModel: CareerHubViewModel = hiltViewModel()
                val navController = rememberNavController()

                CareerHubScaffold(
                    viewModel = viewModel,
                    navController = navController,
                    bottomNavigationItems = bottomNavigationItems
                ) {
                    viewModel.onSelectedNavigationItemIndex(it)
                }
            }
        }
    }
}

