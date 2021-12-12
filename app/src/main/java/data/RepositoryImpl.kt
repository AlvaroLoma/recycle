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
        studentsLiveData.value = ArrayList<Student>(students)
    }
}