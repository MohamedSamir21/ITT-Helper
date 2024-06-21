package com.example.itthelper.career_guidance_hub.data.mapper

import com.example.itthelper.career_guidance_hub.data.model.ContactUsEntity
import com.example.itthelper.career_guidance_hub.domain.model.ContactUs

fun ContactUs.toDataModel(): ContactUsEntity {
    return ContactUsEntity(
        name = this.name,
        email = this.email,
        message = this.message
    )
}