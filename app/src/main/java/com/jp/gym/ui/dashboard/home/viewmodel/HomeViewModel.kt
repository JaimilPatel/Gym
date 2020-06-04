package com.jp.gym.ui.dashboard.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.home.repository.HomeRepository
import java.text.DateFormat
import java.util.*

class HomeViewModel(application: Application) : GymAppViewModel(application) {

    var totalExpense = MutableLiveData<Int>()
    var activeUserCount = MutableLiveData<Int>()
    var inActiveUserCount = MutableLiveData<Int>()
    var dueAmount = MutableLiveData<Int>()
    var receivedAmount = MutableLiveData<Int>()
    var repo = HomeRepository()
    var dateAndTime = MutableLiveData<String>()

    init {
        dateAndTime.postValue("")
        fetchHomeData()
    }

    private fun fetchHomeData(){
        fetchDateAndTime()
        repo.fetchTotalExpense(totalExpense)
        repo.fetchUserCount(activeUserCount)
        repo.fetchUserCount(activeUserCount)
        repo.fetchInActiveUserCount(inActiveUserCount)
        repo.fetchDueAndReceivedPaymentAmount(dueAmount,receivedAmount)
    }

    private fun fetchDateAndTime(){
        val cal = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance().format(cal.time)
        dateAndTime.postValue(currentDate)
    }
}