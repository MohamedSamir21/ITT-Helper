package com.example.itthelper.career_guidance_hub.data.mapper

import com.example.itthelper.career_guidance_hub.data.model.FeedbackEntity
import com.example.itthelper.career_guidance_hub.domain.model.Feedback

fun Feedback.toDataModel(): FeedbackEntity {
    return FeedbackEntity(
        message = this.message
    )
}