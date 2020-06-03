package com.jp.gym.ui.dashboard.expense.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.expense.repository.ExpenseRepository

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
        addExpense("1", trainerName.value!!, itemName.value!!, expenseMoney.value!!, "12")
        trainerName.postValue("")
        itemName.postValue("")
        expenseMoney.postValue("")
        navigateTo.postValue(true)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bind:setMaterialError")
        fun setMaterialError(textInputLayout: TextInputLayout, textInput : String) {
            textInputLayout.error = textInput
        }
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