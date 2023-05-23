package com.example.bottomnav

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnav.modelClasses.goalModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.*


lateinit var pieChart: PieChart
private lateinit var incomeListG: ArrayList<IncomeData>
private lateinit var dbRef: DatabaseReference
class hashIncomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val abc =  inflater.inflate(R.layout.fragment_hash_income, container, false)

        pieChart = abc.findViewById(R.id.pieChartGraph2)
        val list: ArrayList<PieEntry> = ArrayList()
        //incomeListG =  arrayListOf<IncomeData>()

        incomeListG =  arrayListOf<IncomeData>()
        //pieChartIncome()


        //list.add(PieEntry(60f, incomeListG[0].incName.toString()))
        list.add(PieEntry(50000f, "Rental"))
        list.add(PieEntry(220000f, "Salary"))
        list.add(PieEntry(25000f, "FD"))

        dbRef=FirebaseDatabase.getInstance().getReference("Goals")

        /*dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                if(snapshot.exists()){
                    for (incSnap in snapshot.children){
                        val incomeListData = incSnap.getValue(goalModel::class.java)
                        list.add(PieEntry(60f, incomeListData?.goalName))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })*/




        //Pie Chart implementation
        val pieDataSet = PieDataSet(list, "List")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextSize=25f
        pieDataSet.valueTextColor= Color.BLACK

        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.description.text = "Pie Chart"
        pieChart.centerText = "List"
        pieChart.animateY(2000)

        return abc
    }

    /*private fun pieChartIncome(){

        dbRef=FirebaseDatabase.getInstance().getReference("Incomes")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                incomeListG.clear()
                if(snapshot.exists()){
                    for (incSnap in snapshot.children){
                        val incomeListData = incSnap.getValue(IncomeData::class.java)
                        incomeListG.add(incomeListData!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }*/



}