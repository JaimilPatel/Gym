package com.jp.gym.ui.auth

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import com.jp.gym.R
import com.jp.gym.base.GymAppActivity
import com.jp.gym.databinding.ActivityLoginBinding

class LoginActivity : GymAppActivity() {

    private lateinit var mActivityBinding: ActivityLoginBinding

    override fun layoutResource(): Int {
        return R.layout.activity_login
    }

    override fun initializeViewComponents() {
        Navigation.findNavController(this, R.id.navHostFragment)
    }

    override fun postDataBinding(binding: ViewDataBinding?) {
        mActivityBinding = binding as ActivityLoginBinding
        mActivityBinding.lifecycleOwner = this
    }

    override fun getRootView(): View {
        return mActivityBinding.root
    }
}
