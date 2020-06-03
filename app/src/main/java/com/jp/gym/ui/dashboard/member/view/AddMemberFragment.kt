package com.jp.gym.ui.dashboard.member.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentAddMemberBinding
import com.jp.gym.ui.dashboard.member.viewmodel.AddMemberViewModel

class AddMemberFragment : GymAppFragment() {
    private lateinit var mFragmentBinding: FragmentAddMemberBinding
    private val mViewModel: AddMemberViewModel by viewModels()

    override fun layoutResource(): Int {
        return R.layout.fragment_add_member
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentAddMemberBinding
        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {

    }
}