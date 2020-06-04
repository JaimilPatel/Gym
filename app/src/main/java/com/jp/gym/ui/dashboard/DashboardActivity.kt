package com.jp.gym.ui.dashboard

import android.view.Menu
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.jp.gym.R
import com.jp.gym.base.GymAppActivity
import com.jp.gym.databinding.ActivityDashboardBinding
import com.jp.gym.ui.dashboard.location.GymLocationFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : GymAppActivity() {

    private lateinit var mActivityBinding: ActivityDashboardBinding

    override fun layoutResource(): Int {
        return R.layout.activity_dashboard
    }

    override fun initializeViewComponents() {
        val navControl = findNavController(R.id.navDashboardHostFragment)
        navControl.setGraph(R.navigation.nav_dashboard_graph)
        mainBottomNavigation?.setupWithNavController(navControl)
    }

    override fun postDataBinding(binding: ViewDataBinding?) {
        mActivityBinding = binding as ActivityDashboardBinding
        mActivityBinding.lifecycleOwner = this
    }

    override fun getRootView(): View {
        return mActivityBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottombar_dashboard_menu, menu)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
         var navHostFragment = supportFragmentManager.findFragmentById(R.id.navDashboardHostFragment);
        var currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0);
        if(currentFragment !is GymLocationFragment){
            mainBottomNavigation.visibility = View.VISIBLE
        }
    }

}
