package com.example.bottomnav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.bottomnav.modelClasses.goalModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class AddGoal : AppCompatActivity() {


    private lateinit var etGoalName : EditText
    private lateinit var etGoalDescription : EditText
    private lateinit var etGoalConfirmBtn : Button

    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_goal)

        //Get Id of back button
        var returnGoals = findViewById<ImageView>(R.id.back_btn)
        //Back to Goals Fragment
        returnGoals.setOnClickListener {
            /*val fragment = HashGoalFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.viewPagerHash, fragment)
                .addToBackStack(null)
                .commit()*/
            onBackPressed()
        }



        //Assign Ids // Initialize Values
        etGoalName = findViewById(R.id.etGoalName)
        etGoalDescription = findViewById(R.id.etGoalDescription)
        etGoalConfirmBtn = findViewById(R.id.etGoalConfirmBtn)
        dbRef = FirebaseDatabase.getInstance().getReference("Goals")

        etGoalConfirmBtn.setOnClickListener {
            saveGoalData()
        }
    }

    //Create Goal Function
    private fun saveGoalData(){

        //getting values
        val goalName = etGoalName.text.toString()
        val goalDescripion = etGoalDescription.text.toString()
        val goalId = dbRef.push().key!!

        val goal = goalModel(goalId, goalName, goalDescripion)

        dbRef.child(goalId).setValue(goal)
            .addOnCompleteListener{
                Toast.makeText(this, "Goal Added", Toast.LENGTH_LONG).show()

                etGoalName.text.clear()
                etGoalDescription.text.clear()
                onBackPressed()

            }.addOnFailureListener{ err->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}