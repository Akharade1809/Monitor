package org.monitor.shared.domain.interactors.student_home

import org.monitor.shared.domain.entities.Student
import org.monitor.shared.domain.interactors.base.UseCaseIn
import org.monitor.shared.domain.repositories.StudentRepository

class AddStudentUseCase(private val studentRepository: StudentRepository) : UseCaseIn<Student>() {
    override suspend fun block(param: Student) {
        return studentRepository.addStudentToDB(param)
    }
}