package com.example.bottomnav.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnav.modelClasses.AccountModel

class AccSpinnerAdapter(context: Context, accList: List<AccountModel>) : ArrayAdapter<AccountModel>(context, 0, accList) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false)
        }
        getItem(position)?.let {
            (view as TextView).text = it.accountName
        }
        return view!!
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        }
        getItem(position)?.let {
            (view as TextView).text = it.accountName
        }
        return view!!
    }
}

