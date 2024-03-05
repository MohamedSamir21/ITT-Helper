package com.example.itthelper.authentication.presentation.util

import androidx.annotation.DrawableRes
import com.example.itthelper.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
){
    object First: OnBoardingPage(
        image = R.drawable.success,
        title = "Success",
        description = "ITT Helper helps others start their careers by providing guidance, support and resources to empower them on their career journey"
    )

    object Second: OnBoardingPage(
        image = R.drawable.mentorship,
        title = "Mentorship",
        description = "Experts offer advice, share ideas, and serve as role models"
    )

    object Third: OnBoardingPage(
        image = R.drawable.educational_resources,
        title = "Success",
        description = "Providing access to educational resources, workshops, and skill-building opportunities provides them with the tools to succeed"
    )
}
