package com.jp.gym.ui.dashboard.expense.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentAddExpenseBinding
import com.jp.gym.ui.dashboard.expense.viewmodel.AddExpenseViewModel

class AddExpenseFragment : GymAppFragment() {
    private lateinit var mFragmentBinding: FragmentAddExpenseBinding
    private val mViewModel: AddExpenseViewModel by viewModels()

    override fun layoutResource(): Int {
        return R.layout.fragment_add_expense
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentAddExpenseBinding
        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {

    }

}