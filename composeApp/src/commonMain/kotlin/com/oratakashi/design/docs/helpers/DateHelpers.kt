package com.oratakashi.design.docs.helpers

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

object DateHelpers {
    @OptIn(ExperimentalTime::class)
    fun getYear(): String {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.year.toString()
    }
}