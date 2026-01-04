package com.oratakashi.design.docs.helpers

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * DateHelpers provides utility functions for date and time formatting in a platform-agnostic way.
 * @author oratakashi
 * @since 04 Jan 2026
 */
object DateHelpers {
    /**
     * Returns the current year as a string.
     * @return String representation of the current year (e.g., "2026").
     */
    @OptIn(ExperimentalTime::class)
    fun getYear(): String {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.year.toString()
    }

    /**
     * Returns the current time in "HH:mm" format (24-hour clock).
     * @return String representation of the current time in "HH:mm" format.
     */
    fun getTime(): String {
        val time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
        val hour = time.hour.toString().padStart(2, '0')
        val minute = time.minute.toString().padStart(2, '0')
        return "$hour:$minute"
    }
}