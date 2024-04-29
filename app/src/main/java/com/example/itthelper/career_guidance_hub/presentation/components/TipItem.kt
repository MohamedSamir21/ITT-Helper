package com.example.itthelper.career_guidance_hub.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itthelper.career_guidance_hub.presentation.util.CvsTips
import com.example.itthelper.career_guidance_hub.presentation.util.TipItem

@Composable
fun TipItem(
    tipItem: TipItem,
    clicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(
            onClick = clicked
        ) {
            Icon(
                imageVector = tipItem.icon,
                contentDescription = stringResource(id = tipItem.titleId),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = stringResource(id = tipItem.titleId),
                fontSize = 20.sp
            )

        }
        AnimatedVisibility(
            visible = tipItem.showDescription,
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = tipItem.descriptionId),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun TipItemPreview() {
    var tipItem by remember {
        mutableStateOf(CvsTips.tips[0])
    }
    TipItem(
        tipItem
    ) {
        tipItem = tipItem.copy(showDescription = tipItem.showDescription.not())
    }
}