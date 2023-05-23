package com.example.bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bottomnav.modelClasses.goalModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class GoalDetailsActivity : AppCompatActivity() {

    //Declare variables

    //private lateinit var goalId: TextView
    private lateinit var goalName: TextView
    private lateinit var goalDescription: TextView
    private lateinit var btnUpdateGoal: Button
    private lateinit var btnDeleteGoal: Button

    //override onCreate methode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_details)

        //call defined Functions
        initViews()
        setValuesToViews()

        //Set OnClickListner on Update Button
        btnUpdateGoal.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("goalId").toString(),
                intent.getStringExtra("goalName").toString(),
                intent.getStringExtra("goalDescription").toString()
            )
        }

        //Set OnClickListner on Delete Button
        btnDeleteGoal.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("goalId").toString()
            )
        }
    }

    // Initialize values
    private fun initViews(){
        //goalId = findViewById(R.id.goalId)
        goalName = findViewById(R.id.goalName)
        goalDescription = findViewById(R.id.goalDescription)
        btnUpdateGoal = findViewById(R.id.btnUpdateGoal)
        btnDeleteGoal = findViewById(R.id.btnDeleteGoal)
    }


    //Set value to views, From the extra values in the intent
    private fun setValuesToViews(){

        //goalId.text = intent.getStringExtra("goalId")
        goalName.text = intent.getStringExtra("goalName")
        goalDescription.text = intent.getStringExtra("goalDescription")
    }

    //Update Dialog for update details
    private fun openUpdateDialog(goalId: String, goalName:String, goalDescription:String){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updategoal, null)

        mDialog.setView(mDialogView)

        // Initialize Dialog views
         val goalName = mDialogView.findViewById<EditText>(R.id.edGoalName)
         val goalDescription = mDialogView.findViewById<EditText>(R.id.edGoalDescription)
         val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        //Set values to update dialog
        goalName.setText( intent.getStringExtra("goalName").toString())
        goalDescription.setText( intent.getStringExtra("goalDescription").toString())

        mDialog.setTitle("Updating Record")
        var alertDialog = mDialog.create()
        alertDialog.show()

        //onClickListner to update Data Button
        btnUpdateData.setOnClickListener {
            updateGoalData(
                goalId,
                goalName.text.toString(),
                goalDescription.text.toString()
            )

            Toast.makeText(applicationContext, "Goal Data Updated", Toast.LENGTH_LONG).show()


            //We are setting data to textviews
            goalName.text = goalName.text
            goalDescription.text = goalDescription.text

            alertDialog.dismiss()
            onBackPressed()
        }

    }


    //Update Data Function
    private fun updateGoalData(
        id:String,
        name:String,
        description: String

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Goals").child(id)
        val goalInfo = goalModel(id, name, description)
        dbRef.setValue(goalInfo)
    }

    //Delete Function
    private fun deleteRecord(id:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Goals").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Goal is Deleted", Toast.LENGTH_LONG).show()
            onBackPressed()
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}