package com.example.itthelper.career_guidance_hub.presentation.event_workshops

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itthelper.R

class EventsViewModel: ViewModel() {
    private val _state = mutableStateOf(
        EventsScreenState(
            events = listOf(
                Event(
                    R.drawable.educational_resources,
                    "NSP Explore",
                    "Date: January 31, 2024",
                    "Location: Tanta, Al Gharbia"
                ),
                Event(
                    R.drawable.educational_resources,
                    "DevFest",
                    "Date: February 25, 2024",
                    "Location: Shibin-Elkom, Menoufia"
                )
            )
        )
    )
    val state: State<EventsScreenState>
        get() = _state

}