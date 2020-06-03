package com.jp.gym.ui.dashboard.member.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.member.repository.MemberRepository

class AddMemberViewModel(application: Application) : GymAppViewModel(application) {

    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var emailId = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var batch = MutableLiveData<String>()
    var membershipPlan = MutableLiveData<String>()
    var amount = MutableLiveData<String>()
    var paymentStatus = MutableLiveData<String>()
    var repo = MemberRepository(application)

    init {
        firstName.postValue("")
        lastName.postValue("")
        emailId.postValue("")
        mobileNumber.postValue("")
        batch.postValue("")
        membershipPlan.postValue("")
        amount.postValue("")
        paymentStatus.postValue("")
    }

    fun onClickAddMember(view: View) {
        addMember(
            firstName.value!!, lastName.value!!, emailId.value!!, mobileNumber.value!!
            , batch.value!!, membershipPlan.value!!, amount.value!!, paymentStatus.value!!
        )
        firstName.postValue("")
        lastName.postValue("")
        emailId.postValue("")
        mobileNumber.postValue("")
        batch.postValue("")
        membershipPlan.postValue("")
        amount.postValue("")
        paymentStatus.postValue("")
    }

    private fun addMember(
        firstName: String,
        lastName: String,
        emailId: String,
        mobileNumber: String,
        batch: String,
        memberShipPlan: String,
        amount: String,
        paymentStatus: String
    ) {
        repo.addMemberToDatabase(
            firstName,
            lastName,
            emailId,
            mobileNumber,
            batch,
            memberShipPlan,
            amount,
            paymentStatus
        )
    }
}