package com.jp.gym.ui.dashboard.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.home.repository.HomeRepository
import com.jp.gym.utils.preference.SaveSharedPreference
import java.text.DateFormat
import java.util.*

class HomeViewModel(application: Application) : GymAppViewModel(application) {

    var userName = MutableLiveData<String>()
    var totalExpense = MutableLiveData<Int>()
    var activeUserCount = MutableLiveData<Int>()
    var inActiveUserCount = MutableLiveData<Int>()
    var dueAmount = MutableLiveData<Int>()
    var receivedAmount = MutableLiveData<Int>()
    var repo = HomeRepository(application)
    var dateAndTime = MutableLiveData<String>()
    var sharedPreference  = SaveSharedPreference()
    var totalMembers = MutableLiveData<Int>()
    lateinit var profileName : String

    init {
        profileName = sharedPreference.getUsername(application)
        userName.postValue(profileName)
        dateAndTime.postValue("")
        fetchHomeData()
    }

    private fun fetchHomeData(){
        fetchDateAndTime()
        repo.fetchTotalMembers(totalMembers,activeUserCount,inActiveUserCount)
        repo.fetchTotalExpense(totalExpense)
        repo.fetchDueAndReceivedPaymentAmount(dueAmount,receivedAmount)
    }

    private fun fetchDateAndTime(){
        val cal = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance().format(cal.time)
        dateAndTime.postValue(currentDate)
    }
}