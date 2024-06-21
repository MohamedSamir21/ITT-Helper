package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.itthelper.R
import com.example.itthelper.career_guidance_hub.presentation.CareerHubViewModel
import com.example.itthelper.career_guidance_hub.presentation.navigation.BottomNavigationItem
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.career_guidance_hub.presentation.navigation.SetupNavGraph

@Composable
fun CareerHubScaffold(
    viewModel: CareerHubViewModel,
    navController: NavHostController,
    bottomNavigationItems: List<BottomNavigationItem>,
    onSelectedNavigationItemIndex: (Int) -> Unit
) {
    val context = LocalContext.current
    viewModel.updateCurrentRoute(navController.currentBackStackEntryAsState().value?.destination?.route)

    Scaffold(
        topBar = {
            if (viewModel.showTopAppBarValue)
                TopAppBar(navController = navController)
        },
        bottomBar = {
            if (viewModel.showBottomAppBarValue)
                BottomNavigationBar(
                    bottomNavigationItems = bottomNavigationItems,
                    navController = navController,
                ) { newIndex ->
                    onSelectedNavigationItemIndex(newIndex)
                }
        },
        floatingActionButton = {
            if (viewModel.showFABValue)
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.ChatBot.route) }
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
                startDestination = Screen.Home.route,
                onUnauthorized = {
                    viewModel.showUnauthorizedDialog()
                    viewModel.setUnauthorizedMessage(it.asString(context))
                }
            )
            if (viewModel.showUnauthorizedDialogValue)
                UnauthorizedDialog(context, viewModel.unauthorizedMessageValue)
        }
    }
}