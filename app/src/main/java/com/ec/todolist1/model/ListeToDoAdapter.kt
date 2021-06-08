package com.ec.todolist1.model

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ec.todolist1.R

class ListeToDoAdapter(private val actionListener: ActionListener? = null): RecyclerView.Adapter<ListeToDoAdapter.ListeToDoViewHolder>(){

    private val listeListesToDo: MutableList<ListeToDo> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeToDoViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = 2
        return ListeToDoViewHolder(
            itemView = inflater.inflate(layoutId, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ListeToDoViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder position $position")
        holder.bind(listeListesToDo[position])
    }

    override fun getItemCount(): Int = listeListesToDo.size


    class ListeToDoViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val TextView = itemView.findViewById<TextView>(R.id.liste)

        fun bind(listeToDo: ListeToDo) {
            TextView.text = listeToDo.titreListeToDo
        }
    }

    interface ActionListener {
        fun onItemClicked(listeToDo: ListeToDo)
    }

}

