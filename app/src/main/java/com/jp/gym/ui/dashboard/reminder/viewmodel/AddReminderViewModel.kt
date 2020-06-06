package com.jp.gym.ui.dashboard.reminder.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.jp.gym.base.GymAppViewModel
import com.jp.gym.ui.dashboard.reminder.work.ReminderWorker
import java.util.concurrent.TimeUnit

class AddReminderViewModel(application: Application) : GymAppViewModel(application) {

    var eventTitle = MutableLiveData<String>()
    var eventDescription = MutableLiveData<String>()
    var reminderTime = MutableLiveData<String>()
    lateinit var reminderRequest : OneTimeWorkRequest

    init {
        eventTitle.postValue("")
        eventDescription.postValue("")
        reminderTime.postValue("")
    }

    private fun setReminder(){

    }


    fun onClickAddReminder(view: View) {
        val data = Data.Builder()
            .putString("eventTitle", eventTitle.value)
            .putString("eventDescription",eventDescription.value)
            .build()
        var time = reminderTime.value!!
        reminderRequest =
            OneTimeWorkRequest.Builder(ReminderWorker::class.java).setInputData(data).setInitialDelay(time.toLong(), TimeUnit.MINUTES).build()
        setReminder()
        WorkManager.getInstance().enqueue(reminderRequest)
        eventTitle.postValue("")
        eventDescription.postValue("")
        reminderTime.postValue("")
    }
}