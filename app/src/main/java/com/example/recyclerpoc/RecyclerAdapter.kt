package com.example.recyclerpoc

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val list: ArrayList<Details>) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    private var pos : Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)

        return ItemViewHolder(view)
    }

//    fun ChangePos(position: Int){
//        pos = position
//        notifyDataSetChanged()
//    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.name.text = list[position].name
        if (pos != -1 && pos == position){
            holder.container.alpha = 0.4f
        }else{
            holder.container.alpha = 1f
        }
    }

    override fun getItemCount(): Int = list.size

    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val container = itemView.findViewById<CardView>(R.id.container)
    }
}