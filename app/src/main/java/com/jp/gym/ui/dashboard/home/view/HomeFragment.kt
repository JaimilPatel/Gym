package com.jp.gym.ui.dashboard.home.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentHomeBinding
import com.jp.gym.ui.dashboard.home.viewmodel.HomeViewModel

class HomeFragment : GymAppFragment() {

    private lateinit var mFragmentBinding: FragmentHomeBinding
    private val mViewModel: HomeViewModel by viewModels()

    override fun layoutResource(): Int {
        return R.layout.fragment_home
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentHomeBinding
        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {

    }
}