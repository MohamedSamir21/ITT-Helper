package com.example.itthelper.career_guidance_hub.data.mapper

import com.example.itthelper.career_guidance_hub.data.model.EventEntity
import com.example.itthelper.career_guidance_hub.domain.model.Event

fun List<EventEntity>.toDomainEvents(): List<Event> {
    return this.map { Event(it.name, it.time, it.place) }
}