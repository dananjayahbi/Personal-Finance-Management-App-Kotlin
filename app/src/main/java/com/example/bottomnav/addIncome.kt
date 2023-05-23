package com.example.bottomnav

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addIncome : AppCompatActivity() {

    //Getting the views

    private lateinit var etIncomeName:EditText
    private lateinit var etIncomeAmount:EditText
    private lateinit var etIncomeDate:EditText
    private lateinit var etIncomeReccuring:RadioButton
    private lateinit var etIncomeNonReccuring:RadioButton
    private lateinit var etIncomeFrequancy:Spinner
    private lateinit var btnSaveData:Button

    //Database reference
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        //Intitializing
        etIncomeName=findViewById(R.id.incNameValue)
        etIncomeAmount=findViewById(R.id.incAmountValue)
        etIncomeDate=findViewById(R.id.incDateValue)
        etIncomeReccuring=findViewById(R.id.recurring)
        etIncomeFrequancy=findViewById(R.id.spinner)
        btnSaveData=findViewById(R.id.confirm)

        //Initializing
        dbRef=FirebaseDatabase.getInstance().getReference("Incomes")

        btnSaveData.setOnClickListener {
            saveIncomeData()
        }
    }

    private fun saveIncomeData(){
        //getting Values
        val incName=etIncomeName.text.toString()
        val amount=etIncomeAmount.text.toString()
        val incAmount: Double?=amount.toDouble()
        val incDate=etIncomeDate.text.toString()
        val incFrequancy=etIncomeFrequancy.getSelectedItem().toString()
        val incIsEarned:Boolean=false
        var incReccuring: String? =null
        if(etIncomeReccuring.isChecked()){
            incReccuring="Reccuring"
        }else{
            incReccuring="NoN Reccuring"
        }


        //validating
        if(incName.isEmpty()){
            etIncomeName.error="Plese Enter Income Name"
            etIncomeName.requestFocus()
            return
        }
        if(incAmount==null){
            etIncomeAmount.error="Plese Enter Income Amount"
            etIncomeAmount.requestFocus()
            return
        }
        if(incDate.isEmpty()){
            etIncomeDate.error="Plese Enter Income Date"
            etIncomeDate.requestFocus()
            return
        }

        //creating a uneque id for income
        val incId=dbRef.push().key!!

        //Creatig a variable from Income model class
        val income=IncomeModel(incId, incName, incAmount, incDate, incFrequancy, incReccuring, incIsEarned= false)

        //sending to database
        dbRef.child(incId).setValue(income)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted SuccessFully",Toast.LENGTH_LONG).show()
                onBackPressed()
            }.addOnFailureListener { err->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }
    }

}