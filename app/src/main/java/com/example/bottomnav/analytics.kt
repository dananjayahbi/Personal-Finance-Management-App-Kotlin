package com.example.bottomnav

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.bottomnav.adapters.AnalyticsPagerAdapter
import com.example.bottomnav.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class analytics : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    :View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_analytics, container, false)

        /*val goalButton = view.findViewById<Button>(R.id.btn_goals)
        val incomeButton = view.findViewById<Button>(R.id.btn_income)
        val expenseButton = view.findViewById<Button>(R.id.btn_expences)

        val fragmentExpense = hashExpenseFragment()
        val fragmentIncome = hashIncomeFragment.kt()
        val fragmentGoals = hashGoalFragment()

        expenseButton.setOnClickListener{

            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.analysisFragmentsView, fragmentExpense)
            transaction.commit()
        }

        incomeButton.setOnClickListener{

            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.analysisFragmentsView, fragmentIncome)
            transaction.commit()
        }

        goalButton.setOnClickListener{

            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.analysisFragmentsView, fragmentGoals)
            transaction.commit()
        }*/

        //val view=inflater.inflate(R.layout.fragment_income_expense,container,false)

        val tabLayoutHash= view.findViewById<TabLayout>(R.id.tabLayoutHash)
        val viewPagerHash=view.findViewById<ViewPager2>(R.id.viewPagerHash)

        val adapter= AnalyticsPagerAdapter(requireActivity().supportFragmentManager,lifecycle)

        viewPagerHash?.adapter =adapter
        if (tabLayoutHash != null) {
            if (viewPagerHash != null) {
                TabLayoutMediator(tabLayoutHash,viewPagerHash){tab,position->
                    when(position){
                        0->{
                            tab.text="Goals"
                        }
                        1->{
                            tab.text="Income"
                        }
                        2->{
                            tab.text="Expences"
                        }

                    }
                }.attach()
            }
        }

        return view
    }





}