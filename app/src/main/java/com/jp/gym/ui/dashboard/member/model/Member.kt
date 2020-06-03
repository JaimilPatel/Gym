package com.jp.gym.ui.dashboard.member.model

import java.io.Serializable

data class Member(
    var firstName: String,
    var lastName: String,
    var emailId: String,
    var mobileNumber: String,
    var batch : String,
    var memberShipPlan : String,
    var amount : String,
    var paymentStatus : String
) :
    Serializable {

}