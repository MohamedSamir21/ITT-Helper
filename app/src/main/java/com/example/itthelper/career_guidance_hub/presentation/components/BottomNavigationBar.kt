package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.itthelper.career_guidance_hub.presentation.navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    bottomNavigationItems: List<BottomNavigationItem>,
    selectedNavigationItemIndex: Int,
    navController: NavController,
    onUpdateSelectedNavigationItemIndex: (Int) -> Unit
) {
    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, navigationItem ->
            val isItemSelected = index == selectedNavigationItemIndex
            NavigationBarItem(
                selected = isItemSelected,
                onClick = {
                    navController.navigate(bottomNavigationItems[index].screenRoute)
                    onUpdateSelectedNavigationItemIndex(index)
                },
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