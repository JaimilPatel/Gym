package com.jp.gym.ui.dashboard.expense.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentExpenseListBinding
import com.jp.gym.ui.dashboard.expense.adapter.ExpenseAdapter
import com.jp.gym.ui.dashboard.expense.viewmodel.ExpenseListViewModel
import kotlinx.android.synthetic.main.fragment_expense_list.*

class ExpenseListFragment : GymAppFragment() {

    lateinit var mFragmentBinding: FragmentExpenseListBinding
    private lateinit var expenseListAdapter: ExpenseAdapter
    private val mViewModel by viewModels<ExpenseListViewModel>()

    override fun layoutResource(): Int {
        return R.layout.fragment_expense_list
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentExpenseListBinding
        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {
        initUI()

    }

    private fun initUI() {
        expenseListAdapter = ExpenseAdapter()
        rvExpense.adapter = expenseListAdapter

        val animator = rvExpense.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }


}