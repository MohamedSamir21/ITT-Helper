package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.career_guidance_hub.presentation.navigation.Screen
import com.example.itthelper.core.ui.components.AppLogo
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppLogo(
            modifier = Modifier.padding(10.dp),
            fontSize = 25.sp
        )
        TextButton(
            onClick = {
                navController.navigate(Screen.Profile.route)
            },
        ) {
            Icon(
                modifier = Modifier
                    .padding(5.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(3.dp),
                imageVector = Icons.Default.Person,
                contentDescription = "Profile"
            )
        }

    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    ITTHelperTheme {
        TopAppBar(navController = rememberNavController())
    }
}