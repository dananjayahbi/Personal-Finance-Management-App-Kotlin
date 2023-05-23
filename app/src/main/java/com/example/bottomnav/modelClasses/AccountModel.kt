package com.example.bottomnav.modelClasses


//test
data class AccountModel(
    var accountId: String? = "",
    var accountName: String? = "",
    var initialBalance: Double? = 0.0,
    var initialBalanceDate: String? = "",
    var accountType: String? = ""
) {
    constructor() : this("", "", 0.0, "", "")
}
