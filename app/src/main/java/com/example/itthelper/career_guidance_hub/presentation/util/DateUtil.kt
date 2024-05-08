package com.example.itthelper.career_guidance_hub.presentation.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtil {
    fun convertMillisToLocalDate(millis: Long): LocalDate {
        return Instant
            .ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    fun convertLocalDateToString(date: LocalDate): String {
        return DateTimeFormatter
            .ofPattern("MM/dd/yyyy")
            .format(date)
    }
}