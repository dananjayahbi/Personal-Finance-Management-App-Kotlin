package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnav.modelClasses.AccountModel
import com.google.firebase.database.FirebaseDatabase

class AccDetailsActivity : AppCompatActivity() {

    private lateinit var textView10: TextView
    private lateinit var textView18: TextView
    private lateinit var textView19: TextView
    private lateinit var textView20: TextView
    private lateinit var textView21: TextView
    private lateinit var UpdateData: Button
    private lateinit var DeleteAcc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_details)

        // initialize the views
        textView10 = findViewById(R.id.textView10)
        textView18 = findViewById(R.id.textView18)
        textView19 = findViewById(R.id.textView19)
        textView20 = findViewById(R.id.textView20)
        textView21 = findViewById(R.id.textView21)
        UpdateData = findViewById(R.id.UpdateData)
        DeleteAcc = findViewById(R.id.DeleteAcc)

        initView()
        setValuesToViews()

        UpdateData.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("accountId").toString(),
                intent.getStringExtra("accountName").toString()
            )
        }

        DeleteAcc.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("accountId").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Accounts").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Account Deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, AccFetch::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()


        }
    }

    private fun initView() {

    }

    private fun setValuesToViews(){

        textView10.text = intent.getStringExtra("accountId")
        textView18.text = intent.getStringExtra("accountName")
        textView19.text = intent.getStringExtra("accountType")

        val initialBalance = intent.getDoubleExtra("initialBalance", 0.0)
        textView20.text = getString(R.string.currency_format, initialBalance)

        textView21.text = intent.getStringExtra("initialBalanceDate")

    }

    private fun openUpdateDialog(
        accountId: String,
        accountName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_acc_dialog,null)

        mDialog.setView(mDialogView)

        val etAccName = mDialogView.findViewById<EditText>(R.id.etAccName)
        val etAccType = mDialogView.findViewById<EditText>(R.id.etAccType)
        val etInitialBalance = mDialogView.findViewById<EditText>(R.id.etInitialBalance)
        val etInitialBalanceDate = mDialogView.findViewById<EditText>(R.id.etInitialBalanceDate)
        val btnUpdate = mDialogView.findViewById<Button>(R.id.btnUpdate)

        etAccName.setText(intent.getStringExtra("accountName").toString())
        etAccType.setText(intent.getStringExtra("accountType").toString())
        etInitialBalance.setText(intent.getDoubleExtra("initialBalance", 0.0).toString())
        etInitialBalanceDate.setText(intent.getStringExtra("initialBalanceDate").toString())

        mDialog.setTitle("Updating $accountName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener{
            updateAccData(
                accountId,
                etAccName.text.toString(),
                etAccType.text.toString(),
                etInitialBalance.text.toString(),
                etInitialBalanceDate.text.toString()

            )

            Toast.makeText(applicationContext, "Account Data Updated", Toast.LENGTH_LONG).show()

            //Setting updated data into text views
            textView18.text = etAccName.text.toString()
            textView19.text = etAccType.text.toString()
            textView20.text = etInitialBalance.text.toString()
            textView21.text = etInitialBalanceDate.text.toString()

            alertDialog.dismiss()
        }
    }


    private fun updateAccData(
        id: String,
        name: String,
        type: String,
        initBal: String?,
        initBalDate: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Accounts").child(id)
        val initBalDouble = initBal?.toDoubleOrNull() ?: 0.0
        val accInfo = AccountModel(id, name, initBalDouble, initBalDate, type)
        dbRef.setValue(accInfo)
    }



}