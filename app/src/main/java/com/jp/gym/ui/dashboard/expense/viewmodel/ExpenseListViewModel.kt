package com.jp.gym.ui.dashboard.expense.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.expense.model.Expense
import com.jp.gym.ui.dashboard.expense.repository.ExpenseRepository

class ExpenseListViewModel(application: Application) : GymAppViewModel(application) {

    val expenseListLiveData = MutableLiveData<ArrayList<Expense>>()
    var repo = ExpenseRepository()

    init {
        getExpenseList(expenseListLiveData)
    }

    fun getExpenseList(expenseListLiveData : MutableLiveData<ArrayList<Expense>>){
        repo.getExpenseFromDatabase(expenseListLiveData)
    }
}