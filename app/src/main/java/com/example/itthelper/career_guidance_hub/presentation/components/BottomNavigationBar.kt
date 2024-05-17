package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.itthelper.career_guidance_hub.presentation.navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    bottomNavigationItems: List<BottomNavigationItem>,
    navController: NavController,
    onSelectedNavigationItemIndex: (Int) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, navigationItem ->
            val isItemSelected = currentRoute == bottomNavigationItems[index].route
            NavigationBarItem(
                selected = isItemSelected,
                onClick = {
                    navController.navigate(bottomNavigationItems[index].route)
                    onSelectedNavigationItemIndex(index)
                },
                icon = {
                    Icon(
                        imageVector =
                        if (isItemSelected)
                            navigationItem.selectedIcon
                        else
                            navigationItem.unselectedIcon,
                        contentDescription = stringResource(id = navigationItem.titleResId)
                    )
                },
                label = {
                    Text(text = stringResource(id = navigationItem.titleResId))
                }
            )
        }
    }
}