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
fun PhoneTextField(
    modifier: Modifier = Modifier,
    phone: String,
    onPhoneChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = phone,
        label = {
            Text(text = stringResource(R.string.phone))
        },
        singleLine = true,
        onValueChange = { newPhone ->
            onPhoneChange(newPhone)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        )
    )
}

@Preview
@Composable
private fun PhoneTextFieldPreview() {
    ITTHelperTheme {
        PhoneTextField(phone = "") {

        }
    }
}