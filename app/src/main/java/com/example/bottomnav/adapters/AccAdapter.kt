package com.example.bottomnav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.R
import com.example.bottomnav.modelClasses.AccountModel

class AccAdapter (private val accList: ArrayList<AccountModel>) :
    RecyclerView.Adapter<AccAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListener: OnItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.acc_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAcc = accList[position]
        holder.tvAccName.text = currentAcc.accountName
        holder.tvAccBalance.text = currentAcc.initialBalance.toString()

    }

    override fun getItemCount(): Int {
        return accList.size
    }

    class ViewHolder(itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvAccName : TextView = itemView.findViewById(R.id.tvAccName)
        val tvAccBalance: TextView = itemView.findViewById(R.id.tvAccBalance)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}