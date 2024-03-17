package com.example.itthelper.authentication.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.career_guidance_hub.presentation.CareerHubActivity
import com.example.itthelper.authentication.domain.result.AuthResult
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
                val startDestination = viewModel.startDestination.value
                LaunchedEffect(viewModel) {
                    viewModel.authResults.collect { authResult ->
                        when (authResult) {
                            is AuthResult.Authorized -> {
                                Intent(this@AuthActivity, CareerHubActivity::class.java).let {
                                    it.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    this@AuthActivity.startActivity(it)
                                }
                            }
                            else -> Unit
                        }
                    }
                }
                startDestination?.let {
                    AuthFeature(
                        startDestination = startDestination
                    )
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
