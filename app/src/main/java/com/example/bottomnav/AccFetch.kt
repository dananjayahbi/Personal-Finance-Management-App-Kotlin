package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.adapters.AccAdapter
import com.example.bottomnav.modelClasses.AccountModel
import com.google.firebase.database.*

class AccFetch : AppCompatActivity() {
    private lateinit var accRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var accList: ArrayList<AccountModel>
    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acc_fetch)

        accRecyclerView = findViewById(R.id.accRecy)
        accRecyclerView.layoutManager = LinearLayoutManager(this)
        tvLoadingData = findViewById(R.id.loadingData)

        accList = arrayListOf<AccountModel>()

        getAccData()
    }

    private fun getAccData() {
        accRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbref = FirebaseDatabase.getInstance().getReference("Accounts")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                accList.clear()
                if(snapshot.exists()){
                    for(accSnap in snapshot.children){
                        val accData = accSnap.getValue(AccountModel::class.java)
                        accList.add(accData!!)
                    }
                    val mAdapter = AccAdapter(accList)
                    accRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListner(object : AccAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@AccFetch, AccDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("accountId", accList[position].accountId)
                            intent.putExtra("accountName", accList[position].accountName)
                            intent.putExtra("accountType", accList[position].accountType)
                            intent.putExtra("initialBalance", accList[position].initialBalance)
                            intent.putExtra("initialBalanceDate", accList[position].initialBalanceDate)
                            startActivity(intent)
                        }

                    })

                    accRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}