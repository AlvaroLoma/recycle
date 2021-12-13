package com.example.recicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recicle.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import data.RepositoryImpl
import data.entity.Student
import util.observeEvent

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
        MainActivityViewModelFactory(RepositoryImpl, application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        obverveViewModelData()
        addFakeStudent()//PRUEBA TU NI CASO

    }

    private fun obverveViewModelData() {
        observeStudents()
        observeMessages()
        obverveEmptyView()
    }

    private fun obverveEmptyView() {
        viewModel.lblEmtyViewVisibility.observe(this){
            binding.lblEmptyView.visibility=it
        }
    }

    private fun observeMessages() {
        viewModel.onShowMessage.observeEvent(this){
            Snackbar.make(binding.lstStudents,it,Snackbar.LENGTH_SHORT).show()

        }
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
        //binding.lblEmptyView.visibility = if (newList.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    private fun setupViews() {
        setupRecycleView()
        binding.btnMayores.setOnClickListener{ viewModel.getMayores()}
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
        val student: Student= listAdapter.currentList[position]
        Snackbar.make(binding.lstStudents,"borrado",Snackbar.LENGTH_LONG)
            .setAction("texto boton"){
                viewModel.addStudent(student)
            }
            .show()
    }

    private fun editStudent(position: Int) {
        viewModel.editStudent(listAdapter.currentList[position])
    }
    private fun deleteStudent(position: Int) {
        viewModel.deleteStudent(listAdapter.currentList[position])
    }
}