package org.monitor.shared.data.repositoriesImpl

import org.monitor.shared.domain.entities.Student
import org.monitor.shared.data.local.datasource.StudentLocalDataSource
import org.monitor.shared.domain.repositories.StudentRepository

class StudentRepositoryImpl(
    private val studentLocalDataSource: StudentLocalDataSource
) : StudentRepository {
    override suspend fun addStudentToDB(addStudent: Student) {
        println("StdentRepoImpl : adding student :$addStudent")
        studentLocalDataSource.addStudent(student = addStudent)
    }

    override suspend fun getStudent(studentId: String): Student {
        TODO("Not yet implemented")
    }

    override suspend fun getStudentList(): List<Student> {
        TODO("Not yet implemented")
    }
}