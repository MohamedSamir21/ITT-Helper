package com.example.itthelper.career_guidance_hub.presentation.util

sealed class TabContent {
    object CareerPath : TabContent()
    object EmploymentMarket : TabContent()
    object EventsWorkshops : TabContent()
    object CvsTips : TabContent()
    object GuideForInterviews : TabContent()
}
