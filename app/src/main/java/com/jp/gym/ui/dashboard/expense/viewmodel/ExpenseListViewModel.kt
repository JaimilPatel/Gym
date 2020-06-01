package com.jp.gym.ui.dashboard.expense.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.expense.model.Expense
import com.jp.gym.ui.dashboard.expense.repository.ExpenseRepository
import com.jp.gym.utils.ConnectivityUtils

class ExpenseListViewModel(application: Application) : GymAppViewModel(application) {

    val expenseListLiveData = MutableLiveData<ArrayList<Expense>>()
    val expenseLocalListData = MutableLiveData<List<Expense>>()
    var repo = ExpenseRepository(application)
    var context = application

    init {
        getExpenseList(expenseListLiveData)

    }

    private fun getExpenseList(expenseListLiveData: MutableLiveData<ArrayList<Expense>>) {
        if (ConnectivityUtils.isNetworkAvailable(context)) {
            repo.getExpenseFromDatabase(expenseListLiveData)
        } else {
            repo.getDataFromLocal(expenseLocalListData)
        }
    }
}