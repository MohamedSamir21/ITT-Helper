package com.example.itthelper.authentication.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.itthelper.R

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(value) },
        label = {
            Text(text = stringResource(R.string.email_address))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = stringResource(R.string.email)
            )
        },
        supportingText = {
            Text(text = "*" + stringResource(R.string.required))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        modifier = modifier.fillMaxWidth()
    )
}