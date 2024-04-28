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
        image = R.drawable.guidance,
        title = "Guidance and Mentorship",
        description = "Mentorship can provide numerous benefits for mentors and their mentees."
    )

    object Second: OnBoardingPage(
        image = R.drawable.network,
        title = "Networking Opportunities",
        description = "An event that allows you to interact with business professionals to learn new skills."
    )

    object Third: OnBoardingPage(
        image = R.drawable.skill,
        title = "Skill Development",
        description = "The process of improving specific skills by training to be more efficient and effective."
    )
}
