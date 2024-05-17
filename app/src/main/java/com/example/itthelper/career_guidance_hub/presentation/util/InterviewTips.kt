package com.example.itthelper.career_guidance_hub.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import com.example.itthelper.R

object InterviewTips {
    val tips = listOf(
        TipItem(
            titleId = R.string.interview_tip_1_title,
            descriptionId = R.string.interview_tip_1_description,
            icon = Icons.Default.Info
        ),
        TipItem(
            titleId = R.string.interview_tip_2_title,
            descriptionId = R.string.tip_2_description,
            icon = Icons.Default.Description
        ),
        TipItem(
            titleId = R.string.interview_tip_3_title,
            descriptionId = R.string.tip_3_description,
            icon = Icons.Default.QuestionAnswer
        ),
        TipItem(
            titleId = R.string.interview_tip_4_title,
            descriptionId = R.string.interview_tip_4_description,
            icon = Icons.Default.Star
        ),
        TipItem(
            titleId = R.string.interview_tip_5_title,
            descriptionId = R.string.interview_tip_5_description,
            icon = Icons.Default.Work
        ),
        TipItem(
            titleId = R.string.interview_tip_6_title,
            descriptionId = R.string.interview_tip_6_description,
            icon = Icons.Default.Face
        ),
        TipItem(
            titleId = R.string.interview_tip_7_title,
            descriptionId = R.string.interview_tip_7_description,
            icon = Icons.Default.QuestionAnswer
        ),
        TipItem(
            titleId = R.string.interview_tip_8_title,
            descriptionId = R.string.interview_tip_8_description,
            icon = Icons.Default.Person
        ),
        TipItem(
            titleId = R.string.interview_tip_9_title,
            descriptionId = R.string.interview_tip_9_description,
            icon = Icons.Default.AccessTime
        ),
        TipItem(
            titleId = R.string.interview_tip_10_title,
            descriptionId = R.string.interview_tip_10_description,
            icon = Icons.Default.Email
        )
    )
}