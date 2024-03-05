package com.example.itthelper.authentication.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import com.example.itthelper.authentication.presentation.welcome.WelcomeScreen
import com.example.itthelper.ui.theme.ITTHelperTheme

class AuthActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                Log.i("MainActivity", "created")
                WelcomeScreen()
            }
        }
    }
}