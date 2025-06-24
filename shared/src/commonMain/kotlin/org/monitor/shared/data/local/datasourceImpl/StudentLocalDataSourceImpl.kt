package org.monitor.shared.data.local.datasourceImpl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import org.monitor.shared.domain.entities.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject
import org.monitor.shared.base.executor.MainDispatcher
import org.monitor.shared.base.executor.MainIoExecutor
import org.monitor.shared.data.local.datasource.StudentLocalDataSource

class StudentLocalDataSourceImpl : StudentLocalDataSource, MainIoExecutor() {

    private val mainDispatcher: MainDispatcher by inject()
    private val query = database.studentQueries

    override fun getAllStudents(): Flow<List<Student>> {
        return query.getAllStudents()
            .asFlow()
            .mapToList(mainDispatcher.dispatcher)
            .map { list ->
                list.map {
                    Student(
                        studentId = it.studentId,
                        studentFirstName = it.studentFirstName,
                        studentLastName = it.studentLastName,
                        studentMobileNumber = it.studentMobileNumber,
                        studentWhatsappNumber = it.studentWhatsappNumber,
                        studentSchoolName = it.studentSchoolName,
                        studentClass = it.studentClass
                    )
                }
            } ?: flowOf(emptyList())
    }

    override suspend fun getStudentById(id: String): Student? {
        TODO("Not yet implemented")
    }

    override suspend fun addStudent(student: Student) {
        println("StudentLDSImpl : $student")
        query.addStudent(
            studentId = student.studentId,
            studentFirstName = student.studentFirstName,
            studentLastName = student.studentLastName,
            studentMobileNumber = student.studentMobileNumber,
            studentWhatsappNumber = student.studentWhatsappNumber,
            studentSchoolName = student.studentSchoolName,
            studentClass = student.studentClass
        )
    }

}