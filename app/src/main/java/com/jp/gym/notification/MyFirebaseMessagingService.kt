package com.jp.gym.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jp.gym.R
import com.jp.gym.ui.dashboard.DashboardActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    var CHANNEL_ID = "FirebaseNotification"

    override fun onNewToken(p0: String) {
        Log.d(CHANNEL_ID, p0)
        super.onNewToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        createNotification(
            remoteMessage.notification!!.title!!,
            remoteMessage.notification!!.body!!
        )
    }

    private fun createNotificationChannel() {
        val channelName = CHANNEL_ID
        val channelDesc = "channelDesc"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, channelName, importance)
            channel.description = channelDesc

            val notificationManager = getSystemService(NotificationManager::class.java)!!
            val currChannel = notificationManager.getNotificationChannel(CHANNEL_ID)
            if (currChannel == null)
                notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title: String, desc: String) {

        createNotificationChannel()

        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(desc)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        mBuilder.setSound(uri)


        val notificationManager = NotificationManagerCompat.from(this)
        val notificationId = (System.currentTimeMillis() / 4).toInt()
        notificationManager.notify(notificationId, mBuilder.build())
    }

}