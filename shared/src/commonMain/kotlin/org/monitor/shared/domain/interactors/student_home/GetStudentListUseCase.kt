package org.monitor.shared.domain.interactors.student_home

import org.monitor.shared.domain.entities.Student
import org.monitor.shared.domain.interactors.base.UseCaseOut
import org.monitor.shared.domain.repositories.StudentRepository

class GetStudentListUseCase(private val repository: StudentRepository) :  UseCaseOut<List<Student>>() {
    override suspend fun block(): List<Student> {
        return repository.getStudentList()
    }
}