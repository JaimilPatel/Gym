package com.jp.gym.ui.dashboard.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.home.repository.HomeRepository

class HomeViewModel(application: Application) : GymAppViewModel(application) {

    var totalExpense = MutableLiveData<Int>()
    var repo = HomeRepository()

    init {
        totalExpense.postValue(0)
        fetchExpense()
    }

    private fun fetchExpense(){
        repo.fetchTotalExpense(totalExpense)
    }
}