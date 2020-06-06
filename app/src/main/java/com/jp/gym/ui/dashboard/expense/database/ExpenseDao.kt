package com.jp.gym.ui.dashboard.expense.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jp.gym.ui.dashboard.expense.model.Expense


@Dao
interface ExpenseDao {

    @get:Query(
        "SELECT * FROM expenses"
    )
    val allExpense: List<Expense>?

    @Insert
    fun insert(vararg expenses: ExpenseData?)

}