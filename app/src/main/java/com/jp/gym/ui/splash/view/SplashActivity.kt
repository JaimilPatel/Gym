package com.jp.gym.ui.splash.view

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.jp.gym.GymApp
import com.jp.gym.R
import com.jp.gym.base.GymAppActivity
import com.jp.gym.databinding.ActivitySplashBinding
import com.jp.gym.ui.auth.LoginActivity
import com.jp.gym.utils.toast
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : GymAppActivity() {

    private lateinit var mActivityBinding: ActivitySplashBinding
    private lateinit var animation1: Animation
    lateinit var animation2: Animation
    lateinit var animation3: Animation

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

        animation1 = AnimationUtils.loadAnimation(this, R.anim.activity_splashlogorotate)
        animation2 = AnimationUtils.loadAnimation(this, R.anim.activity_splashlogoantirotate)
        animation3 = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in)

        //Start Animation

        ivSplashLogo.startAnimation(animation1)

        //Listener For Animation

        animation1.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                ivSplashLogo.startAnimation(animation2)
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })

        animation2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {

                ivSplashLogo.startAnimation(animation3)

                // Intent Pass To Next Activity

                    val i = Intent(baseContext, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    startActivity(i)
                    finish()
            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })
    }

}
