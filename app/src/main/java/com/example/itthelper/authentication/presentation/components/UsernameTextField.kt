package com.example.itthelper.authentication.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.itthelper.R

@Composable
fun UsernameTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    errorMessage: String,
    disableSupportingText: Boolean = true,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(text = stringResource(R.string.username))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(R.string.username)
            )
        },
        supportingText = {
            if (isError)
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            else if (disableSupportingText)
                Text(text = "")
            else
                Text(text = "*" + stringResource(id = R.string.required))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        isError = isError,
        modifier = modifier.fillMaxWidth(),
    )
}