package com.jp.gym.ui.splash.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.jp.gym.R
import com.jp.gym.base.GymAppActivity
import com.jp.gym.databinding.ActivitySplashBinding
import com.jp.gym.ui.auth.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : GymAppActivity() {

    private lateinit var mActivityBinding: ActivitySplashBinding

    override fun layoutResource(): Int {
        return R.layout.activity_splash
    }

    override fun initializeViewComponents() {
        setUpStatusNavigationBar()
        animationProperties()
    }

    override fun postDataBinding(binding: ViewDataBinding?) {
        mActivityBinding = binding as ActivitySplashBinding
        mActivityBinding.lifecycleOwner = this
    }

    override fun getRootView(): View {
        return mActivityBinding.root
    }

    private fun setUpStatusNavigationBar() {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.blue)
    }

    @SuppressLint("PrivateResource")
    private fun animationProperties() {
        //Start Animation

        val animator = ObjectAnimator.ofFloat(ivSplashLogo, View.ROTATION, -360f, 0f)
        animator.duration = 1000
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                //  ivSplashLogo.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                val i = Intent(baseContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(i)
                finish()
            }
        })
        animator.start()

    }

}
