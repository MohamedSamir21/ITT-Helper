package com.example.itthelper.career_guidance_hub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.components.BottomNavigationBar
import com.example.itthelper.career_guidance_hub.presentation.components.TopAppBar
import com.example.itthelper.career_guidance_hub.presentation.navigation.BottomNavigationItem
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.career_guidance_hub.presentation.navigation.SetupNavGraph
import com.example.itthelper.career_guidance_hub.presentation.navigation.bottomNavigationItems
import com.example.itthelper.core.ui.theme.ITTHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CareerHubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ITTHelperTheme {
                val navController = rememberNavController()
                var selectedNavigationItemIndex by rememberSaveable {
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

@Composable
private fun CareerHubFeature(
    navController: NavHostController,
    bottomNavigationItems: List<BottomNavigationItem>,
    onSelectedNavigationItemIndex: (Int) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var showTopAppBar by rememberSaveable {
        mutableStateOf(true)
    }
    var showBottomAppBar by rememberSaveable {
        mutableStateOf(true)
    }
    var showFAB by rememberSaveable {
        mutableStateOf(true)
    }
    when (navBackStackEntry?.destination?.route) {
        Screen.Profile.route -> {
            showTopAppBar = false
            showBottomAppBar = false
            showFAB = false
        }

        Screen.ContactUs.route -> {
            showTopAppBar = false
            showBottomAppBar = false
            showFAB = false
        }

        Screen.Feedback.route -> {
            showTopAppBar = false
            showBottomAppBar = false
            showFAB = false
        }

        else -> {
            showTopAppBar = true
            showBottomAppBar = true
            showFAB = true
        }
    }

    Scaffold(
        topBar = {
            if (showTopAppBar)
                TopAppBar(navController = navController)
        },
        bottomBar = {
            if (showBottomAppBar)
                BottomNavigationBar(
                    bottomNavigationItems = bottomNavigationItems,
                    navController = navController,
                ) { newIndex ->
                    onSelectedNavigationItemIndex(newIndex)
                }
        },
        floatingActionButton = {
            if (showFAB)
                FloatingActionButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bot),
                        contentDescription = stringResource(R.string.chat_bot)
                    )
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
