package com.example.bottomnav

import android.content.Intent
import android.icu.util.CurrencyAmount
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

class Income_View : AppCompatActivity() {

    private lateinit var incNameValue: TextView
    private lateinit var incAmountValue:TextView
    private lateinit var incDateValue:TextView
    private lateinit var incReccurValue:TextView
    private lateinit var incFrequancyValue:TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnEarned:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_view)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("incId").toString(),
                intent.getStringExtra("incName").toString()
            )
        }

        //Btn Listner to delete
        btnDelete.setOnClickListener {
            deleteIncome(
                intent.getStringExtra("incId").toString()
            )
        }
        //Btn listener to earned
        btnEarned.setOnClickListener {
            setEarned(
                intent.getStringExtra("incId").toString(),
                intent.getStringExtra("incName").toString(),
                intent.getStringExtra("incAmount").toString(),
                intent.getStringExtra("incDate").toString(),
                intent.getStringExtra("incRecurr").toString(),
                intent.getStringExtra("incFrequancy").toString()
            )
        }
    }

    private fun initView(){
        incNameValue = findViewById(R.id.incNameValue)
        incAmountValue = findViewById(R.id.incAmountValue)
        incDateValue = findViewById(R.id.incDateValue)
        incReccurValue = findViewById(R.id.incReccurValue)
        incFrequancyValue = findViewById(R.id.incFrequancyValue)
        btnUpdate=findViewById(R.id.update)
        btnDelete=findViewById(R.id.delete)
        btnEarned=findViewById(R.id.earned)

    }

    private fun setValuesToViews(){
        incNameValue.text=intent.getStringExtra("incName")
        incAmountValue.text= intent.getStringExtra("incAmount");
        incDateValue.text=intent.getStringExtra("incDate")
        incReccurValue.text=intent.getStringExtra("incRecurr")
        incFrequancyValue.text=intent.getStringExtra("incFrequancy")
    }

    //function to the dialog for updating the income
    private fun openUpdateDialog(incId: String,incName: String){
        val mDialog=AlertDialog.Builder(this)
        val inflater=layoutInflater
        val mDialogView=inflater.inflate(R.layout.activity_income_update,null)

        mDialog.setView(mDialogView)

        //initializing the feilds
        val etIncName=mDialogView.findViewById<EditText>(R.id.etIncName)
        val etIncAmount=mDialogView.findViewById<EditText>(R.id.etIncAmount)
        val etIncDate=mDialogView.findViewById<EditText>(R.id.etIncDate)
        val btnUpdateData=mDialogView.findViewById<Button>(R.id.btnUpdateData)

        //setting data to the text feilds
        etIncName.setText( intent.getStringExtra("incName").toString())
        etIncAmount.setText( intent.getStringExtra("incAmount").toString())
        etIncDate.setText( intent.getStringExtra("incDate").toString())

        //setting the unchaged values
        val etRecurr=intent.getStringExtra("incRecurr").toString()
        val etFrequency=intent.getStringExtra("incFrequancy").toString()

        //seting title

        mDialog.setTitle("Updating $incName")

        val alertDialog=mDialog.create()
        alertDialog.show()

        //btn listner to update
        btnUpdateData.setOnClickListener {
            updateIncome(
                incId,
                etIncName.text.toString(),
                etIncAmount.text.toString(),
                etIncDate.text.toString(),
                etRecurr.toString(),
                etFrequency.toString()
            )

            Toast.makeText(applicationContext,"Income Updated",Toast.LENGTH_LONG).show()

            //setting updated data for the text views
            incNameValue.text=etIncName.text.toString()
            incAmountValue.text= etIncAmount.text.toString();
            incDateValue.text=etIncAmount.text.toString()
            incReccurValue.text=intent.getStringExtra("incRecurr")
            incFrequancyValue.text=intent.getStringExtra("incFrequancy")

            alertDialog.dismiss()
        }



    }

    //function to update income
    private fun updateIncome(
        incId: String,
        incName:String,
        incAmount: String,
        incDate: String,
        incRecurr:String,
        incFrequancy:String
    ){
        val dbRef=FirebaseDatabase.getInstance().getReference("Incomes").child(incId)
        var num:Double=incAmount.toDouble()
       //val amount:Long?= num.toLong()
        val incomeInfo=IncomeModel(incId,incName,num,incDate,incFrequancy,incRecurr)
        dbRef.setValue(incomeInfo)
    }

    //function to delete income
    private fun deleteIncome(
        incId: String
    ){
        val dbRef=FirebaseDatabase.getInstance().getReference("Incomes").child(incId)
        val delete=dbRef.removeValue()

        delete.addOnSuccessListener {
            Toast.makeText(this,"Income Deleted Successfully",Toast.LENGTH_LONG).show()
            onBackPressed()
//            val intent=Intent(this,income::class.java)
//            finish()
//            startActivity(intent)
        }.addOnFailureListener { err->
            Toast.makeText(this,"Delete Unsuccessfull $err",Toast.LENGTH_LONG).show()
        }
    }

    //function to set earned income
    private fun setEarned(
        incId: String,
        incName:String,
        incAmount: String,
        incDate: String,
        incRecurr:String,
        incFrequancy:String
    ){
        val dbRef=FirebaseDatabase.getInstance().getReference("Incomes").child(incId)
        var num:Double=incAmount.toDouble()
        val incomeInfo=IncomeModel(incId,incName,num,incDate,incFrequancy,incRecurr, incIsEarned = true)
        dbRef.setValue(incomeInfo).addOnSuccessListener {
            Toast.makeText(this,"Income Updated Successfully",Toast.LENGTH_LONG).show()
            onBackPressed()
        }.addOnFailureListener {err->
            Toast.makeText(this,"Update Unsuccessfull $err",Toast.LENGTH_LONG).show()
        }
    }

}


