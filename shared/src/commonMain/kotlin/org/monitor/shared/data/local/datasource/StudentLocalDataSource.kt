package org.monitor.shared.data.local.datasource

import org.monitor.shared.domain.entities.Student
import kotlinx.coroutines.flow.Flow

interface StudentLocalDataSource : BaseLocalDataSource {
    fun getAllStudents() : Flow<List<Student>>
    suspend fun getStudentById(id : String): Student?
    suspend fun addStudent(student: Student)
}