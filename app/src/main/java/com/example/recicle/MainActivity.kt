package com.example.recicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recicle.databinding.ActivityMainBinding
import data.RepositoryImpl
import data.entity.Student

class MainActivity : AppCompatActivity() {
    /*
        PARA HACER UN RECICLER

            1- Un layaout principal, donde estara el recicler y la vista para cuando la lista este vacia
            2- Un layaout secundario donde defina el aspecto que tendra cada elemento de la lista
            3 - una clase adapter : ver MainActivityAdapter, la cual tendra mi lista de datos
            4 -Un metodo para configurar el recycleView: setupRecycleView()

        CON VIEWMODEL

            1-Creo mi clase viewModel
            2- Para pasarle la base de datos creo que viewModelFactory
            3- Creo mi variable viewModel con su lazy propio
            4- obeservo la lista de datos desde aqui con mi viewModel

     */



    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val listAdapter: MainActivityAdapter = MainActivityAdapter().also {
        it.onEditClickListener1={ position->
            editStudent(position)
        }
        it.onDeleteClickListener1={ position->
            deleteStudent(position)
        }
    }




    private val viewModel: MainActivityViewModel by viewModels{
        MainActivityViewModelFactory(RepositoryImpl)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        observeStudents()
        addFakeStudent()//PRUEBA TU NI CASO

    }

    private fun addFakeStudent() {
        viewModel.addStudent( Student(5,"pepito",22))
    }

    private fun observeStudents() {
        viewModel.students.observe(this,){
            updateList(it)
        }
    }

    private fun updateList(newList: List<Student>) {
        listAdapter.submitList(newList)
        binding.lblEmptyView.visibility = if (newList.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    private fun setupViews() {
        setupRecycleView()
    }

    private fun setupRecycleView() {
        binding.lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)//solo para layaout vertical, si es horizontal
            //usar el contructor con varios valores
           // recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false))
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = listAdapter
        }
    }


    private fun showStudent(position: Int) {
        val student: Student= listAdapter.getStudent(position)
        Toast.makeText(this,student.name,Toast.LENGTH_SHORT).show()
    }

    private fun editStudent(position: Int) {
        viewModel.editStudent(listAdapter.getStudent(position))
    }
    private fun deleteStudent(position: Int) {
        viewModel.deleteStudent(listAdapter.getStudent(position))
    }
}