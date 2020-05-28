package com.jp.gym.ui.dashboard.home.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentHomeBinding

class HomeFragment : GymAppFragment() {

    private lateinit var mFragmentBinding: FragmentHomeBinding

    override fun layoutResource(): Int {
        return R.layout.fragment_home
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentHomeBinding
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {

    }
}