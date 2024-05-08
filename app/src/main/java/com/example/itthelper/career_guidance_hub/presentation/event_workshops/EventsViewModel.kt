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
                    R.drawable.event,
                    "NSP Explore",
                    "25/5/2024",
                    "Tanta, Al Gharbia",
                    "30/4/2024"
                ),
                Event(
                    R.drawable.event,
                    "DevFest",
                    "25/3/2024",
                    "Shibin-Elkom, Menoufia",
                    "30/4/2024"
                )
            )
        )
    )
    val state: State<EventsScreenState>
        get() = _state

}