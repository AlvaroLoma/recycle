package data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import data.entity.Student

object RepositoryImpl : Repository {
    //la lista debe ser mutableList para poder modificarla
    private val students :MutableList<Student> = mutableListOf(
        Student(1, "Baldomero", 18),
        Student(2, "Germán Ginés", 21),
        Student(3, "Rodolfo", 22)
    )
    //variable que mis clases observan desde fuera para avisar de los cambios
    private val studentsLiveData : MutableLiveData<List<Student>> = MutableLiveData(students)

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

    private fun updateStudentLiveData() {
        studentsLiveData.value = ArrayList<Student>(students)
    }
}