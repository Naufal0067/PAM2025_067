package com.example.tugasakhir.uicontroller.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.tugasakhir.R

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.getStringExtra("TITLE") ?: "Pengingat"
        val message = intent.getStringExtra("MESSAGE") ?: ""
        val channelId =
            intent.getStringExtra("CHANNEL_ID") ?: NotificationHelper.CHANNEL_JADWAL_ID

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // WAJIB ADA
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
