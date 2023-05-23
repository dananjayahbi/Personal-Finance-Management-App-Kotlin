package com.example.bottomnav

import android.icu.util.CurrencyAmount

data class IncomeModel (
    var incId:String?=null,
    var incName:String?=null,
    var incAmount:Double?=0.0,
    var incDate:String?=null,
    var incFrequancy:String?=null,
    var incReccuring:String?=null,
    var incNonReccuring:String?=null,
    var incIsEarned:Boolean?=false
)

