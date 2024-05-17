package com.example.itthelper.career_guidance_hub.presentation.components

import android.content.Context
import android.content.Intent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.itthelper.R
import com.example.itthelper.authentication.presentation.AuthActivity

@Composable
fun UnauthorizedDialog(context: Context, message: String) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    Intent(context, AuthActivity::class.java).let {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(it)
                    }
                }
            ) {
                Text(text = stringResource(R.string.ok))
            }
        }
    )
}