package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itthelper.R
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    message: String,
    onMessageChange: (String) -> Unit
) {
    TextField(
        modifier = modifier.height(150.dp),
        value = message,
        label = {
            Text(text = stringResource(R.string.your_message))
        },
        onValueChange = { newMessage ->
            onMessageChange(newMessage)
        }
    )
}

@Preview
@Composable
private fun MessageTextFieldPreview() {
    ITTHelperTheme {
        MessageTextField(message = "") {

        }
    }
}