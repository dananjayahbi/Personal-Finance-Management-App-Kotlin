package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.adapters.GoalAdapster
import com.example.bottomnav.modelClasses.goalModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class HashGoalFragment : Fragment() {


    private lateinit var goalRecyclerView: RecyclerView
    private lateinit var goalName: TextView
    private lateinit var goalDescription: TextView

    private lateinit var goalList: ArrayList<goalModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var ret =  inflater.inflate(R.layout.fragment_hash_goal, container, false)

        //add button
        var addGoals = ret.findViewById<Button>(R.id.add_btn)
        addGoals.setOnClickListener {
            val intent = Intent(context, AddGoal::class.java)
            requireContext().startActivity(intent)
        }

        var calculator = ret.findViewById<Button>(R.id.calBtn)
        calculator.setOnClickListener {
            val intent = Intent(context, HashCalculator::class.java)
            requireContext().startActivity(intent)
        }


        goalRecyclerView = ret.findViewById<RecyclerView>(R.id.goalRecyclerView)
        goalRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        goalRecyclerView.setHasFixedSize(true)

        goalList =  arrayListOf<goalModel>()

        getGoalData()

        return ret
    }


    //Retrieve Goals Data From Database
    private fun getGoalData(){
        goalRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Goals")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              goalList.clear()
                if(snapshot.exists()){
                    for (goalSnap in snapshot.children){
                        val goalData = goalSnap.getValue(goalModel::class.java)
                        goalList.add(goalData!!)
                    }
                    val mAdapter = GoalAdapster(goalList)
                    goalRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListner(object: GoalAdapster.onItemClickListner{
                        override fun onItemClick(position: Int) {
                           val intent = Intent(requireContext(), GoalDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("goalId", goalList[position].goalId)
                            intent.putExtra("goalName", goalList[position].goalName)
                            intent.putExtra("goalDescription", goalList[position].goalDescription)
                            startActivity(intent)
                        }

                    })
                    goalRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


}