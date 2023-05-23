package com.example.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.cos

class expense : Fragment(R.layout.fragment_expense) {

    private lateinit var adapterExp: expenseAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseArrayList: ArrayList<expenseData>

    lateinit var names: Array<String>
    lateinit var dates:Array<String>
    lateinit var costs:Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager=LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.listView)
        recyclerView.layoutManager=layoutManager
        recyclerView.hasFixedSize()
        adapterExp=expenseAdapter(expenseArrayList)
        recyclerView.adapter=adapterExp

    }



    private fun dataInitialize(){
        expenseArrayList = arrayListOf<expenseData>()

        names= arrayOf(
            "Food",
            "Trasport",
            "Clothing",
            "Grocery"
        )

        dates= arrayOf(
            "14/3/2022",
            "16/3/2022",
            "15/3/2022",
            "20/3/2022",
        )

        costs= arrayOf(
            "Rs.15,000",
            "Rs.5,000",
            "Rs.7,000",
            "Rs.10,000",
        )

        for(i in names.indices){
            val data=expenseData(names[i],dates[i],costs[i])
            expenseArrayList.add(data)
        }

    }

}