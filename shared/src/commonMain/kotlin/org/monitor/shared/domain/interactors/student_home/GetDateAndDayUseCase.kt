package org.monitor.shared.domain.interactors.student_home

import org.monitor.shared.domain.entities.DateAndDay
import org.monitor.shared.domain.interactors.base.UseCaseOut
import org.monitor.shared.domain.repositories.DateAndDayRepository

class GetDateAndDayUseCase(private val repository : DateAndDayRepository) :
    UseCaseOut<DateAndDay>() {
    override suspend fun block(): DateAndDay {
        return repository.getDateAndDay()
    }

}