package com.example.bottomnav

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class hashExpenseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var ret =  inflater.inflate(R.layout.fragment_hash_expense, container, false)

        pieChart = ret.findViewById(R.id.pieChartGraph1)
        val list: ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(50000f, "Lease"))
        list.add(PieEntry(30000f, "Food"))
        list.add(PieEntry(10000f, "Travel"))
        list.add(PieEntry(10000f, "Medicine"))
        list.add(PieEntry(20000f, "House Rent"))

        val pieDataSet = PieDataSet(list, "List")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextSize=25f
        pieDataSet.valueTextColor= Color.BLACK

        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.description.text = "Pie Chart"
        pieChart.centerText = "List"
        pieChart.animateY(2000)

        return ret
    }


}