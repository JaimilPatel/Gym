package com.jp.gym.ui.dashboard.member.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.member.model.Member
import com.jp.gym.ui.dashboard.member.repository.MemberRepository

class MemberListViewModel(application: Application) : GymAppViewModel(application) {

    val memberListLiveData = MutableLiveData<ArrayList<Member>>()
    var repo = MemberRepository(application)
    var context = application

    init {
        getMemberList(memberListLiveData)

    }

    private fun getMemberList(expenseListLiveData: MutableLiveData<ArrayList<Member>>) {
        repo.getMemberFromDatabase(expenseListLiveData)
    }


}