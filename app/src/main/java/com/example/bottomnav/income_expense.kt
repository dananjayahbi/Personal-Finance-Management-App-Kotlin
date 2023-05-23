package com.example.bottomnav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.bottomnav.adapters.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class income_expense : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_income_expense,container,false)

        val tabLayout= view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=view.findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter= ViewPagerAdapter(requireActivity().supportFragmentManager,lifecycle)

        viewPager2?.adapter =adapter
        if (tabLayout != null) {
            if (viewPager2 != null) {
                TabLayoutMediator(tabLayout,viewPager2){tab,position->
                    when(position){
                        0->{
                            tab.text="Income"
                        }
                        1->{
                            tab.text="Expense"
                        }
                    }
                }.attach()
            }
        }

        return view
    }




}
