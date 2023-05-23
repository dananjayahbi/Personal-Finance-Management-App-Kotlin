package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnav.adapters.AccSpinnerAdapter
import com.example.bottomnav.modelClasses.AccountModel
import com.example.bottomnav.Accounts
import com.google.firebase.database.*
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation.findNavController


class Transfer : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var senderSpinner: Spinner
    private lateinit var receiverSpinner: Spinner
    private lateinit var accountList: List<AccountModel>
    private lateinit var fromAccount: AccountModel
    private lateinit var toAccount: AccountModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_transfer)

        senderSpinner = findViewById(R.id.senderSpinner)
        receiverSpinner = findViewById(R.id.receiverSpinner)

        //Get Id of back button
        var returnBtn = findViewById<ImageButton>(R.id.imageButton77)

        //Back to Accounts Fragment
        returnBtn.setOnClickListener {
            onBackPressed()
        }


        dbref = FirebaseDatabase.getInstance().reference.child("Accounts")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tempList = ArrayList<AccountModel>()
                for (accSnap in snapshot.children) {
                    val account = accSnap.getValue(AccountModel::class.java)
                    account?.let { tempList.add(it) }
                }
                accountList = tempList
                loadSpinnerData()
            }

            override fun onCancelled(error: DatabaseError) {
                // handle error
            }
        })

        findViewById<View>(R.id.btnTransfer).setOnClickListener {
            val fromAccount = senderSpinner.selectedItem as AccountModel
            val toAccount = receiverSpinner.selectedItem as AccountModel
            val transferAmount = findViewById<EditText>(R.id.editTextNumber2).text.toString().toDouble()
            transferFunds(fromAccount, toAccount, transferAmount)

        }


    }

    private fun loadSpinnerData() {
        val senderAdapter = AccSpinnerAdapter(this, accountList)
        val receiverAdapter = AccSpinnerAdapter(this, accountList)

        senderSpinner.adapter = senderAdapter
        receiverSpinner.adapter = receiverAdapter
    }

    private fun transferFunds(fromAccount: AccountModel, toAccount: AccountModel, transferAmount: Double) {
        fromAccount.initialBalance = fromAccount.initialBalance!! - transferAmount
        toAccount.initialBalance = toAccount.initialBalance!! + transferAmount
        dbref.child(fromAccount.accountId!!).setValue(fromAccount)
        dbref.child(toAccount.accountId!!).setValue(toAccount)

        Toast.makeText(this, "Transfer complete", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, AccountsActivity::class.java)
        startActivity(intent)


    }


}
