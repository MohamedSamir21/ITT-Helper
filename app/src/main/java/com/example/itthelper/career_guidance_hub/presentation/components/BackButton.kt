package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun BackButton(
    navController: NavController
) {
    TextButton(
        onClick = {
            navController.popBackStack()
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = "Go back"
        )
    }
}

@Preview
@Composable
private fun BackButtonPreview() {
    ITTHelperTheme {
        BackButton(navController = rememberNavController())
    }
}