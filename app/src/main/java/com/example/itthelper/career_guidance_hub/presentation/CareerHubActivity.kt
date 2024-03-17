package com.example.itthelper.career_guidance_hub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.itthelper.career_guidance_hub.presentation.home.HomeScreen
import com.example.itthelper.career_guidance_hub.presentation.home.HomeViewModel
import com.example.itthelper.career_guidance_hub.presentation.navigation.NavigationItem
import com.example.itthelper.core.ui.theme.ITTHelperTheme

class CareerHubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ITTHelperApp()
                }
            }
        }
    }

    @Composable
    private fun ITTHelperApp(){
        val homeViewModel by viewModels<HomeViewModel>()
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                selectedIcon = Icons.Default.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false,
            ),
            NavigationItem(
                title = "Courses",
                selectedIcon = Icons.Default.PlayArrow,
                unselectedIcon = Icons.Outlined.PlayArrow,
                hasNews = false,
            ),
            NavigationItem(
                title = "Training",
                selectedIcon = Icons.Default.Face,
                unselectedIcon = Icons.Outlined.Face,
                hasNews = false,
            ),
        )

        HomeScreen(
            navigationItems = navigationItems,
            tabs = listOf("Career path", "Employment Market", "Events & Workshop"),
            selectedNavigationItemIndex = homeViewModel.selectedNavigationItemIndex,
            selectedTabIndex = homeViewModel.selectedTabIndex,
            onSelectedNavigationItemIndex = { newSelectedIndex ->
                homeViewModel.updateSelectedIndex(newSelectedIndex)
            },
            onSelectedTabIndex = { newSelectedIndex ->
                homeViewModel.updateSelectedTabIndex(newSelectedIndex)

            }
        )
    }
}
