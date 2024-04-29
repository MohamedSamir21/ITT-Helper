package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itthelper.R

@Composable
fun TipHeadline(
    modifier: Modifier,
    icon: ImageVector,
    iconTint: Color,
    text: String,
    textColor: Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                tint = iconTint,
                contentDescription = stringResource(R.string.tips),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = text,
                color = textColor
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TipHeadlinePreview() {
    TipHeadline(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        icon = Icons.Rounded.Lightbulb,
        iconTint = MaterialTheme.colorScheme.onTertiaryContainer,
        text = stringResource(id = R.string.guides_for_making_your_cv),
        textColor = MaterialTheme.colorScheme.onTertiaryContainer
    )
}