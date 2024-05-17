package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.itthelper.R

@Composable
fun RetryComponent(
    modifier: Modifier = Modifier,
    message: String,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = horizontalAlignment
    ) {
        Text(text = message)
        Button(
            onClick = onRetryClicked
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}