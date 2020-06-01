package com.jp.gym.ui.dashboard.expense.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "expenses",indices = [Index(value = arrayOf("trainer_name"), unique = true)])
data class ExpenseData(
    @field:PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @field:ColumnInfo(name = "trainer_name")
    var trainer_name: String = "",

    @field:ColumnInfo(name = "expense_date")
    var expense_date: String = "",

    @field:ColumnInfo(name = "item_name")
    var item_name: String = "",

    @field:ColumnInfo(name = "item_money")
    var item_money: String = ""
)