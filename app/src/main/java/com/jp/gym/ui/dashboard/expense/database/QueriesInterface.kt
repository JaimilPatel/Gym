package com.jp.gym.ui.dashboard.expense.database

import com.jp.gym.ui.dashboard.expense.model.Expense

interface QueriesInterface {

    fun getAllExpense(): ArrayList<Expense>?

    fun insertThreads(vararg threads: ExpenseData): Int

}