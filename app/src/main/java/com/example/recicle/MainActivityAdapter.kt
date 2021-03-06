package com.example.recicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recicle.databinding.MainItemBinding
import data.entity.Student

class MainActivityAdapter(): ListAdapter<Student,MainActivityAdapter.ViewHolder>(studentDiffCallback) {//clase interna que o creo
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


    var onEditClickListener1: ((Int)-> Unit)?=null  //OJO EN LOS APUNTES SE HACE DE OTRA FORMA
    var onDeleteClickListener1: ((Int)-> Unit)?=null

    //avisa al adaptador de crear un nuevo viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //parent = recycleView
        val layaoutInflater=LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(layaoutInflater,parent,false)
        return ViewHolder(binding)

    }
    //me da la posicion que se tiene que pintar
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = currentList[position]
        holder.bind(student)
    }


     inner class ViewHolder(private val binding: MainItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            init {
                //binding.clRoot.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }
                binding.buttonEdit.setOnClickListener{
                    val position = bindingAdapterPosition
                    if(position!=RecyclerView.NO_POSITION){
                        onEditClickListener1?.invoke(position)
                    }

                }
                binding.buttonDelete.setOnClickListener{
                    onDeleteClickListener1?.invoke(adapterPosition)
                }
            }
            fun bind(student: Student){
                binding.lblName.text=student.name
                //binding.lblAge.text=itemView.context.resources.getQuantityString(R.plurals.main_item_age,age,age)
                binding.lblAge.text=student.age.toString()
            }

    }


    object studentDiffCallback: DiffUtil.ItemCallback<Student> (){

        //son iguales?
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean
                = oldItem.id== newItem.id

        //mismos datos?
        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem==newItem //compara todos los datos HASTA LOS QUE NO SE VEN
        //SE PUEDE HACER UNO POR UNO USANDO EL &&


    }



}

