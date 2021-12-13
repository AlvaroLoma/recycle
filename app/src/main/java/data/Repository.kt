package data

import androidx.lifecycle.LiveData
import data.entity.Student

interface Repository {

    fun queryAllStudents(): LiveData<List<Student>>
    fun addStudent(student: Student)
    fun updateStudent(newStudent: Student)
    fun deleteStudent(student: Student)
    fun deleteAllStudents()
    fun queryMayores() :LiveData<List<Student>>
}