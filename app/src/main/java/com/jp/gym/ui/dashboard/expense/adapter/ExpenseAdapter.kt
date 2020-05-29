package com.jp.gym.ui.dashboard.expense.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jp.gym.R
import com.jp.gym.databinding.ItemExpenseBinding
import com.jp.gym.ui.dashboard.expense.model.Expense

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private val expenseList = ArrayList<Expense>()

    class ExpenseViewHolder(val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setViewItem(resExpense: Expense) {
            binding.expenseItemInfo = resExpense
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {
        val expenseListBinding: ItemExpenseBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_expense, parent, false)
        return ExpenseViewHolder(expenseListBinding)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.setViewItem(expenseList[position])
    }

    fun setExpenseList(list: List<Expense>) {
        expenseList.clear()
        expenseList.addAll(list)
        notifyDataSetChanged()
    }
}