package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itthelper.core.ui.theme.ITTHelperTheme

@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 15.sp,
    infoTitle: String,
    infoValue: String,
) {
    ITTHelperTheme {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$infoTitle: $infoValue",
                fontSize = fontSize,
                color = MaterialTheme.colorScheme.secondary
            )
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Check Circle",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun InfoItemPreview() {
    InfoItem(infoTitle = "Date", infoValue = "22/7/2024")
}