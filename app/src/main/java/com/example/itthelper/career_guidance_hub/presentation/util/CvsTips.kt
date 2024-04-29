package com.example.itthelper.career_guidance_hub.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FormatClear
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Spellcheck
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.filled.Work
import com.example.itthelper.R

object CvsTips {
    val tips = listOf(
        TipItem(
            titleId = R.string.cv_tip_1_title,
            descriptionId = R.string.cv_tip_1_description,
            icon = Icons.Default.FormatClear
        ),
        TipItem(
            titleId = R.string.cv_tip_2_title,
            descriptionId = R.string.cv_tip_2_description,
            icon = Icons.Default.ContactMail
        ),
        TipItem(
            titleId = R.string.cv_tip_3_title,
            descriptionId = R.string.cv_tip_3_description,
            icon = Icons.Default.AccountBox
        ),
        TipItem(
            titleId = R.string.cv_tip_4_title,
            descriptionId = R.string.cv_tip_4_description,
            icon = Icons.Default.Work
        ),
        TipItem(
            titleId = R.string.cv_tip_5_title,
            descriptionId = R.string.cv_tip_5_description,
            icon = Icons.Default.School
        ),
        TipItem(
            titleId = R.string.cv_tip_6_title,
            descriptionId = R.string.cv_tip_6_description,
            icon = Icons.Default.Code
        ),
        TipItem(
            titleId = R.string.cv_tip_7_title,
            descriptionId = R.string.cv_tip_7_description,
            icon = Icons.Default.Star
        ),
        TipItem(
            titleId = R.string.cv_tip_8_title,
            descriptionId = R.string.cv_tip_8_description,
            icon = Icons.Default.Settings
        ),
        TipItem(
            titleId = R.string.cv_tip_9_title,
            descriptionId = R.string.cv_tip_9_description,
            icon = Icons.AutoMirrored.Filled.TrendingUp
        ),
        TipItem(
            titleId = R.string.cv_tip_10_title,
            descriptionId = R.string.cv_tip_10_description,
            icon = Icons.Default.Spellcheck
        ),
        TipItem(
            titleId = R.string.cv_tip_11_title,
            descriptionId = R.string.cv_tip_11_description,
            icon = Icons.Default.ContentCopy
        ),
        TipItem(
            titleId = R.string.cv_tip_12_title,
            descriptionId = R.string.cv_tip_12_description,
            icon = Icons.Default.AttachFile
        ),
        TipItem(
            titleId = R.string.cv_tip_13_title,
            descriptionId = R.string.cv_tip_13_description,
            icon = Icons.Default.FilterAlt
        ),
        TipItem(
            titleId = R.string.cv_tip_14_title,
            descriptionId = R.string.cv_tip_14_description,
            icon = Icons.Default.Update
        )
    )
}