package com.example.recicle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import data.Repository
import data.entity.Student

//como le quiero pasar el repositorio tengo que crear un factory
class MainActivityViewModel(val repository: Repository): ViewModel() {


    val students : LiveData<List<Student>> = repository.queryAllStudents()
    fun addStudent(student: Student) {
        repository.addStudent(student)

    }



}