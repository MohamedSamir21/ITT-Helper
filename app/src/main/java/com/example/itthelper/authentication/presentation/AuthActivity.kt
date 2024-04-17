package com.example.itthelper.authentication.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.career_guidance_hub.presentation.CareerHubActivity
import com.example.itthelper.authentication.presentation.navigation.SetupNavGraph
import com.example.itthelper.core.ui.theme.ITTHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                val viewModel: AuthViewModel = viewModel()
                val loginStatus = viewModel.loginStatus.value
                val startDestination = viewModel.startDestination.value
                val isLoading = viewModel.isLoading.value

                if (loginStatus) {
                    Intent(this@AuthActivity, CareerHubActivity::class.java).let {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK
                        this.startActivity(it)
                    }
                } else {
                    if (!isLoading)
                        AuthFeature(startDestination = startDestination)
                }
            }
        }
    }
}


@Composable
fun AuthFeature(
    startDestination: String
) {
    val navController = rememberNavController()

    SetupNavGraph(
        navController = navController,
        startDestination = startDestination
    )
}
