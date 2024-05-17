package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.itthelper.R
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun NameTextField(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = name,
        label = {
            Text(text = stringResource(R.string.name))
        },
        singleLine = true,
        onValueChange = { newName ->
            onNameChange(newName)
        }
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun NameTextFieldPreview() {
    ITTHelperTheme {
        NameTextField(name = "") {

        }
    }
}