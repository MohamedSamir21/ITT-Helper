package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.itthelper.R
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun SendButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClicked,
        shape = RectangleShape
    ) {
        Text(text = stringResource(R.string.send))
    }
}

@Preview
@Composable
private fun SendButtonPreview() {
    ITTHelperTheme {
        SendButton {}
    }
}