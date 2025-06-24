package org.monitor.shared.data.repositoriesImpl

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.monitor.shared.domain.entities.DateAndDay
import org.monitor.shared.domain.repositories.DateAndDayRepository

class DateAndDayRepositoryImpl : DateAndDayRepository {
    override suspend fun getDateAndDay(): DateAndDay {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val date = now.date.toString()
        val day = now.date.dayOfWeek.name.lowercase().replaceFirstChar { it.titlecase() }
        return DateAndDay(date = date, day= day)
    }
}

