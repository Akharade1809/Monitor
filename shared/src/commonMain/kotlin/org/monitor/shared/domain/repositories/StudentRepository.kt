package org.monitor.shared.domain.repositories

import org.monitor.shared.domain.entities.Student


interface StudentRepository : Repository {
    suspend fun addStudentToDB(addStudent : Student)
    suspend fun getStudent(studentId : String) : Student
    suspend fun getStudentList() : List<Student>
}