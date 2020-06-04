package com.jp.gym.ui.dashboard.member.viewmodel

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.jp.gym.R
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.member.model.Member
import com.jp.gym.ui.dashboard.member.repository.MemberRepository

class MemberListViewModel(application: Application) : GymAppViewModel(application) {

    val memberListLiveData = MutableLiveData<ArrayList<Member>>()
    var repo = MemberRepository(application)
    var context = application
    var navigateTo = MutableLiveData<Boolean>()
    var paymentStatus = MutableLiveData<Boolean>()

    init {
        getMemberList(memberListLiveData)
        paymentStatus.postValue(false)
        fetchPaymentStatus()

    }

    fun onClickAddMember(view: View) {
        navigateTo.postValue(true)
    }

    private fun getMemberList(expenseListLiveData: MutableLiveData<ArrayList<Member>>) {
        repo.getMemberFromDatabase(expenseListLiveData)
    }

    private fun fetchPaymentStatus() {
        repo.fetchPaymentStatus(paymentStatus)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bind:setImageResource")
        fun setImageResource(imageView: ImageView, status: String) {
            if (status == "Paid")
                imageView.setImageResource(R.drawable.ic_green_dot_24dp)
            else
                imageView.setImageResource(R.drawable.ic_red_dot_24dp)
        }
    }


}