package com.jp.gym.ui.dashboard.reminder.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jp.gym.R


class ReminderWorker(context: Context, workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    @NonNull
    override fun doWork(): Result {
        val title = inputData.getString("eventTitle")
        val message = inputData.getString("eventDescription")
        sendNotification(title, message)
        return Result.success()
    }

    private fun sendNotification(title: String?, message: String?) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //If on Oreo then notification required a notification channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        val notification =
            NotificationCompat.Builder(applicationContext, "default")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher_round)
        notificationManager.notify(1, notification.build())
    }
}