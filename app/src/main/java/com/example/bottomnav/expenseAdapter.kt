package com.example.bottomnav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class expenseAdapter(private val arrayList: ArrayList<expenseData>):RecyclerView.Adapter<expenseAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): expenseAdapter.MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.cardtest,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: expenseAdapter.MyViewHolder, position: Int) {
        val currentItem=arrayList[position]
        holder.eName.text=currentItem.name
        holder.date.text=currentItem.dueDate
        holder.cost.text=currentItem.amount

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val eName:TextView=itemView.findViewById(R.id.incName)
        val date:TextView=itemView.findViewById(R.id.tvdate)
        val cost:TextView=itemView.findViewById(R.id.tvAmount)
    }

}