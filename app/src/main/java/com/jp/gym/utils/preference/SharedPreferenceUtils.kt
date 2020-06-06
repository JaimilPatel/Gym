package com.jp.gym.utils.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SaveSharedPreference {

    private fun getPrefernces(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveUsername(context: Context, userName: String) {
        var editor: SharedPreferences.Editor = getPrefernces(context).edit()
        editor.putString(USER_NAME, userName)
        editor.apply()
    }

    fun getUsername(context: Context): String {
        return getPrefernces(context).getString(USER_NAME, "").toString()
    }

    fun saveUserId(context: Context, userId: String) {
        var editor: SharedPreferences.Editor = getPrefernces(context).edit()
        editor.putString(USER_ID, userId)
        editor.apply()
    }

    fun getUserId(context: Context): String {
        return getPrefernces(context).getString(USER_ID, "").toString()
    }


    //Declare Constants
    companion object {
        const val USER_NAME = "userName"
        const val USER_ID = "userId"
    }
}