package com.example.itthelper.career_guidance_hub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.career_guidance_hub.presentation.components.BottomNavigationBar
import com.example.itthelper.career_guidance_hub.presentation.navigation.BottomNavigationItem
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.career_guidance_hub.presentation.navigation.SetupNavGraph
import com.example.itthelper.core.ui.components.AppLogo
import com.example.itthelper.core.ui.theme.ITTHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CareerHubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                val navController = rememberNavController()
                val bottomNavigationItems = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        screenRoute = Screen.Home.route,
                        selectedIcon = Icons.Default.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false,
                    ),
                    BottomNavigationItem(
                        title = "Courses",
                        screenRoute = Screen.Courses.route,
                        selectedIcon = Icons.Default.PlayArrow,
                        unselectedIcon = Icons.Outlined.PlayArrow,
                        hasNews = false,
                    ),
                    BottomNavigationItem(
                        title = "Training",
                        screenRoute = Screen.Training.route,
                        selectedIcon = Icons.Default.Face,
                        unselectedIcon = Icons.Outlined.Face,
                        hasNews = false,
                    ),
                )
                var selectedNavigationItemIndex by remember {
                    mutableIntStateOf(0)
                }
                CareerHubFeature(
                    navController = navController,
                    bottomNavigationItems = bottomNavigationItems
                ) {
                    selectedNavigationItemIndex = it
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CareerHubFeature(
    navController: NavHostController,
    bottomNavigationItems: List<BottomNavigationItem>,
    selectedNavigationItemIndex: Int = 0,
    onSelectedNavigationItemIndex: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            AppLogo(
                modifier = Modifier.padding(10.dp),
                fontSize = 25.sp
            )
        },
        bottomBar = {
            BottomNavigationBar(
                bottomNavigationItems = bottomNavigationItems,
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                navController = navController,
            ) { newIndex ->
                onSelectedNavigationItemIndex(newIndex)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SetupNavGraph(
                navController = navController,
                startDestination = Screen.Home.route
            )
        }
    }
}
