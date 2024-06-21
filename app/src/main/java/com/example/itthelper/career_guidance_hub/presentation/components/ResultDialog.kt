package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.itthelper.R

@Composable
fun ResultDialog(
    modifier: Modifier = Modifier,
    messageTitle: String,
    messageBody: String,
    isSuccessful: Boolean,
    contentDescription: String,
    onConfirmedClicked: () -> Unit = {},
    onDismissedClicked: () -> Unit = {},
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { if (!isSuccessful) onDismiss() },
        confirmButton = {
            if (isSuccessful) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onConfirmedClicked
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        },
        dismissButton = {
            if (!isSuccessful) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onDismissedClicked,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
        },
        title = {
            Text(
                text = messageTitle,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = messageBody,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        icon = {
            Icon(
                imageVector = if (isSuccessful) Icons.Default.CheckCircle else Icons.Default.Dangerous,
                contentDescription = contentDescription,
                tint = if (isSuccessful) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        },
        modifier = modifier
    )
}