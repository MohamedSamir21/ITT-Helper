package com.example.itthelper.authentication.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.itthelper.R

@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.create_account)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun RegisterButtonPreview() {
    RegisterButton()
}