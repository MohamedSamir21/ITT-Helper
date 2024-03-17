package com.example.itthelper.career_guidance_hub.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.example.itthelper.career_guidance_hub.presentation.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigationItems: List<NavigationItem>,
    tabs: List<String>,
    selectedNavigationItemIndex: Int = 0,
    selectedTabIndex: Int? = null,
    onSelectedNavigationItemIndex: (Int) -> Unit,
    onSelectedTabIndex: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            append("ITT")
                            pushStyle(
                                SpanStyle(
                                    color = Color.Red
                                )
                            )
                            append("Helper")
                        }
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, navigationItem ->
                    val isItemSelected = index == selectedNavigationItemIndex

                    NavigationBarItem(
                        selected = isItemSelected,
                        onClick = { onSelectedNavigationItemIndex(index) },
                        icon = {
                            Icon(
                                imageVector =
                                if (isItemSelected)
                                    navigationItem.selectedIcon
                                else
                                    navigationItem.unselectedIcon,
                                contentDescription = navigationItem.title
                            )
                        },
                        label = {
                            Text(text = navigationItem.title)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            if (selectedTabIndex != null) {
                TabRow(selectedTabIndex = selectedTabIndex) {
                    tabs.forEachIndexed { index, tabTitle ->
                        Tab(
                            selected = index == selectedTabIndex,
                            onClick = {
                                onSelectedTabIndex(index)
                            },
                            text = {
                                Text(text = tabTitle)
                            }
                        )
                    }
                }
            }
        }
    }
}