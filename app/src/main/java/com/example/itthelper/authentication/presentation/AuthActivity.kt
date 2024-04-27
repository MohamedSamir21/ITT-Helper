package com.example.itthelper.authentication.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.core.ui.theme.ITTHelperTheme
import com.example.itthelper.career_guidance_hub.presentation.CareerHubActivity
import com.example.itthelper.authentication.presentation.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: AuthViewModel by viewModels()
        installSplashScreen().setKeepOnScreenCondition {
            Log.d("AuthActivityTest", viewModel.isLoading.value.toString())
            viewModel.isLoading.value
        }
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (viewModel.loginStatus.value) {
                        Intent(
                            this@AuthActivity,
                            CareerHubActivity::class.java
                        ).let {
                            it.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    }
                    AuthFeature(startDestination = viewModel.startDestination.value)
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
