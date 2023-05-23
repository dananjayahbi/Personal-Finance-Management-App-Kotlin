package com.example.bottomnav

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.bottomnav.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(home())

        val firebase:DatabaseReference=FirebaseDatabase.getInstance().getReference()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            replaceFragment(income_expense())//changed here for testing income_expense
        }

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(home())
                R.id.accounts -> replaceFragment(Accounts())
                R.id.analytics -> replaceFragment(analytics())
                R.id.profile -> replaceFragment(profile())
                else -> {

                }
            }
            true
        }

 }


        private fun replaceFragment(fragment: Fragment) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, fragment)
            fragmentTransaction.commit()
        }
    }

