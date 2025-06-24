package org.monitor.shared.domain.repositories

import org.monitor.shared.domain.entities.DateAndDay

interface DateAndDayRepository : Repository {
    suspend fun getDateAndDay() : DateAndDay
}