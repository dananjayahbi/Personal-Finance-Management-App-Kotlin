package com.example.bottomnav

data class IncomeData (var incId:String? = "",
                      var incName:String? = "",
                      var incDate: String? = "",
                      var incAmount: Long? = 0,
                      var incReccuring: String? = "",
                      var incFrequancy: String? = "",
                       var incIsEarned:Boolean?=false
                      )
