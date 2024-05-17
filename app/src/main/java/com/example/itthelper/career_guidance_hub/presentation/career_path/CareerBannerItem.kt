package com.example.itthelper.career_guidance_hub.presentation.career_path

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.itthelper.R

data class CareerBannerItem(
    @StringRes
    val titleResId: Int,
    @StringRes
    val descriptionResId: Int,
    @DrawableRes
    val imageResId: Int
)

object CareerBanner {
    val items = listOf(
        CareerBannerItem(
            titleResId = R.string.mediator,
            descriptionResId = R.string.helping_communicate_with_company_owners_and_experienced_people,
            imageResId = R.drawable.mediator
        ),
        CareerBannerItem(
            titleResId = R.string.chat_bot_services,
            descriptionResId = R.string.student_assistant_to_ask_about_any_information,
            imageResId = R.drawable.chat
        ),
        CareerBannerItem(
            titleResId = R.string.training_and_employment_opportunities,
            descriptionResId = R.string.helping_to_boost_individual_and_organizational_performance,
            imageResId = R.drawable.intern
        ),
        CareerBannerItem(
            titleResId = R.string.guides_for_cvs_and_interviews,
            descriptionResId = R.string.helping_stand_out_from_the_crowd_and_secure_a_job_interview,
            imageResId = R.drawable.guide
        ),
        CareerBannerItem(
            titleResId = R.string.virtual_employment_fair,
            descriptionResId = R.string.enabling_job_seekers_to_quickly_reach_out_to_the_employers_in_a_virtual_space,
            imageResId = R.drawable.vr
        ),
        CareerBannerItem(
            titleResId = R.string.providing_events_and_workshops,
            descriptionResId = R.string.sharing_insights_experiences_and_expertise,
            imageResId = R.drawable.event
        ),
    )
}