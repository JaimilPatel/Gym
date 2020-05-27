package com.jp.gym

import android.app.Application

class GymApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: GymApp? = null
    }

}