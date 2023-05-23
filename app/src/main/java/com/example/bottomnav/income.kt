package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlin.math.exp


class income : Fragment(R.layout.fragment_income) {

    private lateinit var adapterInc: incomeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var incomeArrayList: ArrayList<IncomeData>
    private lateinit var dbRef:DatabaseReference
    private lateinit var incExpected:TextView
    private lateinit var incCurrent:TextView


    lateinit var names: Array<String>
    lateinit var dates:Array<String>
    lateinit var costs:Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_income, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager= LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.recycle)
        recyclerView.layoutManager=layoutManager
        recyclerView.hasFixedSize()
        incomeArrayList = arrayListOf<IncomeData>()
        initView()
        dataInitialize()

        //setIncome()


            val addinc=view.findViewById<Button>(R.id.button)

            addinc.setOnClickListener {
                requireActivity().run{
                    startActivity(Intent(this,addIncome::class.java))
                    finish()
                }
            }


    }

    private fun initView(){
        incExpected= view!!.findViewById(R.id.tvExpectedExpAmount)
        incCurrent=view!!.findViewById(R.id.tvCurrExpAmount)
    }

    public fun setIncome(incomeArrayList:ArrayList<IncomeData>){

        var expectedTotal:Long=0;
        var currentTotal:Long=0;
        for(item in incomeArrayList){
            expectedTotal += item.incAmount!!.toLong()
            if(item.incIsEarned==true){
                currentTotal+=item.incAmount!!.toLong()
            }
        }
        var value:Double=expectedTotal.toDouble()
        var value2:Double=currentTotal.toDouble()
        incExpected.text= "Rs.$value"
        incCurrent.text="Rs.$value2"
    }


    private fun dataInitialize(){


        dbRef=FirebaseDatabase.getInstance().getReference("Incomes")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                incomeArrayList.clear()
                if(snapshot.exists()){
                    for(incSnap in snapshot.children){
                        val incData=incSnap.getValue(IncomeData::class.java)
                        incomeArrayList.add(incData!!)
                    }
                    adapterInc=incomeAdapter(incomeArrayList)
                    recyclerView.adapter=adapterInc
                    setIncome(incomeArrayList)
                    adapterInc.setOnItemClickListner(object : incomeAdapter.onItemClickListner {
                        override fun onItemClick(prosition: Int) {
                            val intent=Intent(requireContext(),Income_View::class.java);
                            val d =incomeArrayList[prosition].incAmount.toString();
                            //putting extras
                            intent.putExtra("incId",incomeArrayList[prosition].incId);
                            intent.putExtra("incName",incomeArrayList[prosition].incName);
                            intent.putExtra("incAmount",d);
                            intent.putExtra("incDate",incomeArrayList[prosition].incDate);
                            intent.putExtra("incRecurr",incomeArrayList[prosition].incReccuring);
                            intent.putExtra("incFrequancy",incomeArrayList[prosition].incFrequancy);
                            intent.putExtra("incIsEarned",incomeArrayList[prosition].incIsEarned);

                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

//        names= arrayOf(
//            "Salary",
//            "Bonus",
//            "Dividends",
//            "Rental",
//            "Allowances",
//            "Uber",
//            "FD Intrest"
//        )
//
//        dates= arrayOf(
//            "14/3/2022",
//            "16/3/2022",
//            "15/3/2022",
//            "20/3/2022",
//            "14/3/2022",
//            "14/3/2022",
//            "16/3/2022"
//        )
//
//        costs= arrayOf(
//            "Rs.15,000",
//            "Rs.5,000",
//            "Rs.7,000",
//            "Rs.10,000",
//            "Rs.15,000",
//            "Rs.15,000",
//            "Rs.5,000"
//        )
//
//        for(i in names.indices){
//            val data=incomeData(names[i],dates[i],costs[i])
//            incomeArrayList.add(data)
//        }

    }

}