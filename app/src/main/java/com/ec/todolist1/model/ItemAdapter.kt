package com.ec.todolist1.model

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.ec.todolist1.R

class ItemAdapter(private val dataset: List<ItemToDo>, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cb: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.itemtodo, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.cb.text = item.description
        holder.cb.isChecked = item.fait
        holder.view.setOnClickListener{
            itemClickListener.onItemClicked(holder.cb, position)
        }
    }

    override fun getItemCount() = dataset.size
}

interface OnItemClickListener {
    fun onItemClicked(v: View, pos: Int)
}