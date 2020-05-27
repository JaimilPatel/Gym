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
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : GymAppActivity() {

    private lateinit var mActivityBinding: ActivityDashboardBinding

    override fun layoutResource(): Int {
        return R.layout.activity_dashboard
    }

    override fun initializeViewComponents() {
        setSupportActionBar(bottomAppBar)
        val navController = Navigation.findNavController(this, R.id.navDashboardHostFragment)
        bottomAppBar.replaceMenu(R.menu.bottombar_dashboard_menu)
        navController.setGraph(R.navigation.nav_dashboard_graph)
        bottomAppBar.setupWithNavController(navController)

// Connect MenuItems to the NavController
        bottomAppBar.setOnMenuItemClickListener {  menuItem ->
            menuItem.onNavDestinationSelected(navController)
        }
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


}
