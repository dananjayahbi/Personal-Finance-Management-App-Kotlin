package com.example.bottomnav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AccountsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_accounts)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.accounts_container, Accounts())
        fragmentTransaction.commit()
    }
}
