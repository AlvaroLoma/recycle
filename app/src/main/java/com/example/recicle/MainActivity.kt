package com.example.recicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recicle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*
        PARA HACER UN RECICLER

            1- Un layaout principal, donde estara el recicler y la vista para cuando la lista este vacia
            2- Un layaout secundario donde defina el aspecto que tendra cada elemento de la lista
            3 - una clase adapter : ver MainActivityAdapter, la cual tendra mi lista de datos
            4 -Un metodo para configurar el recycleView: setupRecycleView()

     */



    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val listAdapter: MainActivityAdapter = MainActivityAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
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
}