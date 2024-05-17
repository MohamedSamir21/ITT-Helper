package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.itthelper.R
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = email,
        label = {
            Text(text = stringResource(id = R.string.email))
        },
        singleLine = true,
        onValueChange = { newEmail ->
            onEmailChange(newEmail)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        )
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun EmailTextFieldPreview() {
    ITTHelperTheme {
        EmailTextField(email = "") {

        }
    }
}