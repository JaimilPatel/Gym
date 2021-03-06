package com.jp.gym.ui.dashboard.expense.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentExpenseListBinding
import com.jp.gym.ui.dashboard.expense.adapter.ExpenseAdapter
import com.jp.gym.ui.dashboard.expense.database.DatabaseService
import com.jp.gym.ui.dashboard.expense.model.Expense
import com.jp.gym.ui.dashboard.expense.viewmodel.ExpenseListViewModel
import kotlinx.android.synthetic.main.fragment_expense_list.*
import kotlinx.android.synthetic.main.fragment_member_list.*

class ExpenseListFragment : GymAppFragment() {

    lateinit var mFragmentBinding: FragmentExpenseListBinding
    private lateinit var expenseListAdapter: ExpenseAdapter
    private val mViewModel by viewModels<ExpenseListViewModel>()
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var database: DatabaseService
    lateinit var allExpenses: List<Expense>

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
        mViewModel.expenseListLiveData.observe(this, Observer {
            if(it!=null && it.size>0){
                expenseListAdapter.setExpenseList(it)
                rvExpense?.adapter = expenseListAdapter
                rvProgressExpense.visibility = View.GONE
                tvNoExpense.visibility= View.GONE
            }else{
                rvProgressExpense.visibility = View.GONE
                tvNoExpense.visibility= View.VISIBLE
            }
        })
        mViewModel.expenseLocalListData.observe(this, Observer {
            it?.let {
                if (it.size > 0) {
                    expenseListAdapter.setExpenseList(it)
                    Log.d("ListFromLocal", "$it")
                    rvExpense?.adapter = expenseListAdapter
                    rvProgressExpense.visibility = View.GONE
                }
            }
        })
        mViewModel.navigateTo.observe(this, Observer {
            it?.let {
                if (it) {
                    this.findNavController().navigate(R.id.addExpense)
                    //  mViewModel.navigateTo.postValue(false)
                }
            }
        })
    }

    private fun initUI() {
        mViewModel.navigateTo.postValue(false)
        expenseListAdapter = ExpenseAdapter()
        rvExpense.adapter = expenseListAdapter
        val animator = rvExpense.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }


}