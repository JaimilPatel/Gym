package com.jp.gym.ui.dashboard.expense.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.expense.repository.ExpenseRepository
import com.jp.gym.utils.preference.SaveSharedPreference
import java.text.DateFormat
import java.util.*

class AddExpenseViewModel(application: Application) :
    GymAppViewModel(application) {

    val trainerName = MutableLiveData<String>()
    val itemName = MutableLiveData<String>()
    val expenseMoney = MutableLiveData<String>()
    var context = application
    private var repo = ExpenseRepository(context)
    var navigateTo = MutableLiveData<Boolean>()

    init {
        trainerName.postValue("")
        itemName.postValue("")
        expenseMoney.postValue("")
        navigateTo.postValue(false)
    }

    fun onClickAddExpense(view: View) {
        Log.d("AddExpense", trainerName.value!!)
        val cal = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance().format(cal.time)
        val sharedPreference = SaveSharedPreference()
        val userId = sharedPreference.getUserId(context)
        addExpense(currentDate, trainerName.value!!, itemName.value!!, expenseMoney.value!!, userId)
        trainerName.postValue("")
        itemName.postValue("")
        expenseMoney.postValue("")
        navigateTo.postValue(true)
    }

    private fun addExpense(
        date: String,
        trainer: String,
        item: String,
        expense: String,
        userId: String
    ) {
        repo.addExpenseToDatabase(date, trainer, item, expense, userId)
    }

}