package com.jp.gym.ui.dashboard.expense.database

import android.content.Context
import com.jp.gym.ui.dashboard.expense.model.Expense

class DatabaseService(context: Context) : QueriesInterface {

    private val dao: ExpenseDao = GymAppDatabase.getInstance(context).repoDao()

    override fun getAllExpense(): ArrayList<Expense> {
        return dao.allExpense as ArrayList<Expense>
    }

    override fun insertThreads(vararg threads: ExpenseData): Int {
        return try {
            dao.insert(*threads)
            1
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

}