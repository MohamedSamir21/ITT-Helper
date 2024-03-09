package com.example.itthelper.authentication.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.TextUnit

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    fontSize: TextUnit
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append("ITT")
            pushStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            append("Helper")
        },
        fontSize = fontSize
    )
}