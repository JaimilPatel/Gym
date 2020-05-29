package com.jp.gym.ui.dashboard.expense.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.expense.repository.ExpenseRepository

class AddExpenseViewModel(application: Application) :
    GymAppViewModel(application) {

    val trainerName = MutableLiveData<String>()
    val itemName = MutableLiveData<String>()
    val expenseMoney = MutableLiveData<String>()
    private var repo = ExpenseRepository()

    init {
        trainerName.postValue("")
        itemName.postValue("")
        expenseMoney.postValue("")
    }

    fun onClickAddExpense(view: View) {
        Log.d("AddExpense", trainerName.value!!)
        addExpense("1", trainerName.value!!, itemName.value!!, expenseMoney.value!!,"12")
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