package com.example.recicle

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import data.Repository
import data.entity.Student
import util.Event

//como le quiero pasar el repositorio tengo que crear un factory
//variable applicacion para obtener recursos de la actividad
class MainActivityViewModel(val repository: Repository,private val application: Application): ViewModel() {

    val queryTrigger :MutableLiveData<Int> = MutableLiveData()
    init {
        queryTrigger.value=0
    }
  var students :LiveData<List<Student>> = queryTrigger.switchMap {
      if(it == 0){
          repository.queryAllStudents()
      }else{
          repository.queryMayores()
      }
  }




    //val students : LiveData<List<Student>> = Transformations.map(originalStudents){ originalList ->
      //  originalList.filter { it.age<18 }
            //.count()
    //}


    val lblEmtyViewVisibility: LiveData<Int> = Transformations.map(students){
        if(it.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    //eventos
    private val _onShowMessage : MutableLiveData<Event<String>> = MutableLiveData()//comunicacion entre la actividad y elviewmodel
    val onShowMessage : LiveData<Event<String>>
        get() = _onShowMessage

    fun addStudent(student: Student) {
        repository.addStudent(student)

    }

    fun editStudent(student: Student) {
        val newStudent = student.copy(age=student.age+1)
        repository.updateStudent(newStudent)
    }

    fun deleteStudent(student: Student) {
        if(students.value?.isNotEmpty()==true){
            repository.deleteStudent(student)
        }else{
            _onShowMessage.value =Event(application.getString(R.string.app_name))
            //
        }

    }

    fun getMayores() {
        queryTrigger.value=18
    }


}