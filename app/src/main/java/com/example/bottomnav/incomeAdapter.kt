package com.example.bottomnav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class incomeAdapter (private val arrayList: ArrayList<IncomeData>): RecyclerView.Adapter<incomeAdapter.MyViewHolder>(){

    private lateinit var mListner: onItemClickListner
    interface onItemClickListner{
        fun onItemClick(prosition: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        mListner=clickListner;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): incomeAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.cardtest,parent,false)
        return MyViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: incomeAdapter.MyViewHolder, position: Int) {
        val currentItem=arrayList[position]
        holder.eName.text=currentItem.incName
        holder.date.text=currentItem.incDate
        holder.cost.text=currentItem.incAmount.toString()

    }



    override fun getItemCount(): Int {
        return arrayList.size
    }


    class MyViewHolder(itemView: View, clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView){
        val eName: TextView =itemView.findViewById(R.id.incName)
        val date: TextView =itemView.findViewById(R.id.tvdate)
        val cost: TextView =itemView.findViewById(R.id.tvAmount)

        init {
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}
