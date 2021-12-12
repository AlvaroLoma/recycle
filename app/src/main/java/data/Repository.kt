package data

import androidx.lifecycle.LiveData
import data.entity.Student

interface Repository {

    fun queryAllStudents(): LiveData<List<Student>>
    fun addStudent(student: Student)
}