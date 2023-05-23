package com.example.bottomnav


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class Accounts : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_accounts, container, false)

        // Add button
        val addAcc = view.findViewById<Button>(R.id.button5)
        val fetchaccs = view.findViewById<Button>(R.id.fetchbtn)
        val balTrans = view.findViewById<Button>(R.id.TransferBetAccBtn)


        addAcc.setOnClickListener {
            val intent = Intent(requireContext(), AddAccountActivity::class.java)
            startActivity(intent)
        }

        fetchaccs.setOnClickListener{
            val intent = Intent(requireContext(), AccFetch::class.java)
            startActivity(intent)
        }

        balTrans.setOnClickListener{
            val intent = Intent(requireContext(), Transfer::class.java)
            startActivity(intent)
        }



        return view
    }

}
