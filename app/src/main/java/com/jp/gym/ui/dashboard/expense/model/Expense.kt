package com.jp.gym.ui.dashboard.expense.model

import java.io.Serializable

data class Expense(
    var trainer_name: String,
    var expense_date: String,
    var item_name: String,
    var item_money: String
) :
    Serializable {

}