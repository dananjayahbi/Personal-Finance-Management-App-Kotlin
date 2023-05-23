package com.example.bottomnav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.R
import com.example.bottomnav.modelClasses.goalModel

//Adapter for Get Goals List
class GoalAdapster(private val goalList: ArrayList<goalModel>): RecyclerView.Adapter<GoalAdapster.ViewHolder>() {


    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        mListner = clickListner
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.goals,parent,false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: GoalAdapster.ViewHolder, position: Int) {
        val currentGoal = goalList[position]
        holder.goalName.text = currentGoal.goalName
        holder.goalDescription.text = currentGoal.goalDescription

    }

    override fun getItemCount(): Int {
       return goalList.size
    }

    class ViewHolder(itemView: View, clickListner: onItemClickListner) : RecyclerView.ViewHolder(itemView){

        val goalName : TextView = itemView.findViewById(R.id.retGoalName)
        val goalDescription : TextView = itemView.findViewById(R.id.retGoalDescription)

        init {
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }


}