package ru.appricot.medreg.model.repository

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.firebase.messaging.FirebaseMessagingService
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import ru.appricot.medreg.R
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d("MSG", remoteMessage!!.notification!!.body)
        shownotification(remoteMessage.notification)
    }

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        Log.d("NEW_TOKEN", s)
    }


    fun shownotification(message: RemoteMessage.Notification?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "ru.appricot.medreg" //your app package name

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, "Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationChannel.description = "Techrush Channel"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)

        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(message!!.title)
            .setSmallIcon(R.drawable.ic_bell)
            .setContentText(message.body)
            .setContentInfo("Info")

        notificationManager.notify(Random().nextInt(), notificationBuilder.build())
    }
}
