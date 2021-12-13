package data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import data.entity.Student

object RepositoryImpl : Repository {
    //la lista debe ser mutableList para poder modificarla
    private val students :MutableList<Student> = mutableListOf(
        Student(1, "Baldomero", 15),
        Student(2, "Germán Ginés", 17),
        Student(3, "Rodolfo", 22)
    )
    //variable que mis clases observan desde fuera para avisar de los cambios
    private val studentsLiveData : MutableLiveData<List<Student>> = MutableLiveData()
    private val mayoresLiveData :LiveData<List<Student>> = studentsLiveData.map { list ->
        list.filter { it.age>18 }
    }
    init {
        updateStudentLiveData()
    }

    override fun queryAllStudents(): LiveData<List<Student>> {
        return studentsLiveData
    }

   override fun addStudent(student: Student){
        students.add(student)
       updateStudentLiveData()
    }

    override fun updateStudent(newStudent: Student) {
        val position = students.indexOfFirst { it.id==newStudent.id }//busca el estudiante
        if(position>=0){//si es mayor que -1 es que lo encontro
            students.set(position,newStudent)//machaca la posicion
             updateStudentLiveData()//aviso de cambios
        }
    }

    override fun deleteStudent(student: Student) {
        val position = students.indexOfFirst { it.id==student.id }//busca el estudiante
        if(position>=0){//si es mayor que -1 es que lo encontro
            students.removeAt(position)//borra esa posicion
            updateStudentLiveData()//aviso de cambios
        }
    }

    override fun deleteAllStudents() {
        students.clear()
        updateStudentLiveData()
    }

    override fun queryMayores() :LiveData<List<Student>> = mayoresLiveData

    private fun updateStudentLiveData() {
        studentsLiveData.value = students.sortedBy { it.id }
    }
}