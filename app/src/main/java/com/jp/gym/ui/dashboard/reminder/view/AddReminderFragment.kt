package com.jp.gym.ui.dashboard.reminder.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentAddReminderBinding
import com.jp.gym.ui.dashboard.reminder.viewmodel.AddReminderViewModel

class AddReminderFragment : GymAppFragment() {

    private lateinit var mFragmentBinding: FragmentAddReminderBinding
    private val mViewModel: AddReminderViewModel by viewModels()

    override fun layoutResource(): Int {
        return R.layout.fragment_add_reminder
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentAddReminderBinding
        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {

    }

}