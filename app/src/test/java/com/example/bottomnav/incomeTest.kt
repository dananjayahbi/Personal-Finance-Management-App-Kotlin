package com.example.bottomnav

import org.junit.Assert.*
import org.junit.Test

class incomeTest{

    private lateinit var incomeArrayList: ArrayList<IncomeData>
    lateinit var names: Array<String>
    lateinit var dates:Array<String>
    lateinit var costs:Array<String>

    val income=income()

    fun createArr(){
        names= arrayOf(
            "Salary",
            "Bonus",
            "Dividends"
        )

        dates= arrayOf(
            "14/3/2022",
            "16/3/2022",
            "15/3/2022",
        )

        costs= arrayOf(
            "15000",
            "10000",
            "20000"
        )

        for(i in names.indices){
            val data=IncomeData(names[i],dates[i],costs[i], incIsEarned = true)
            incomeArrayList.add(data)
        }
    }

    @Test
    fun setIncome_isCorrect(){
        incomeArrayList = arrayListOf<IncomeData>()
        createArr()
        assertEquals(45000,income.setIncome(incomeArrayList))
    }


}