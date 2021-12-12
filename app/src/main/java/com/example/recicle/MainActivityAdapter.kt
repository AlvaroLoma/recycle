package com.example.recicle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recicle.databinding.MainItemBinding
import data.entity.Student

class MainActivityAdapter(): RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {
    /*

        Como hacer un adapter:
            1- tener una clase interna llamada Viewholder que reciba el binding del layaout del item de la lista
            2- la clase debe extender a RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() la cual implementa la clase interna del adapter
            3- Implementar los metodos:
                onCreateViewHolder dice como crear el viewHolder
                getItemCount() numero de elementos que tendra el recycler
                onBindViewHolder como pintar cada elemento, llamar al viewHolder
            4- Una lista de datos y un metodo para actualizarla

     */

    private var data: List<Student> = emptyList();

    //avisa al adaptador de crear un nuevo viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //parent = recycleView
        val layaoutInflater=LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(layaoutInflater,parent,false)
        return ViewHolder(binding)

    }
    //me da la posicion que se tiene que pintar
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = data[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = data.size

    //actualizar la lista
    fun submitList(newList :List<Student>){
        data = newList
        notifyDataSetChanged()//avisa que la fuente de datos cambio

    }
    class ViewHolder(private val binding: MainItemBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(student: Student){
                binding.lblName.text=student.name
                //binding.lblAge.text=itemView.context.resources.getQuantityString(R.plurals.main_item_age,age,age)
                binding.lblAge.text=student.age.toString()
            }

    }

}

