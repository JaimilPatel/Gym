package com.jp.gym.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class GymAppActivity : AppCompatActivity() {

    abstract fun layoutResource(): Int
    protected abstract fun initializeViewComponents()
    abstract fun postDataBinding(binding: ViewDataBinding?)
    abstract fun getRootView(): View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this, layoutResource()) as ViewDataBinding?
        postDataBinding(binding)
        initializeViewComponents()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)//ask
    }


}