package com.example.tugasakhir.uicontroller.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object NotificationHelper {

    const val CHANNEL_JADWAL_ID = "jadwal_channel"
    const val CHANNEL_TUGAS_ID = "tugas_channel"

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Channel Jadwal
            val jadwalChannel = NotificationChannel(
                CHANNEL_JADWAL_ID,
                "Notifikasi Jadwal Pelajaran",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifikasi pengingat jadwal pelajaran"
            }

            // Channel Tugas
            val tugasChannel = NotificationChannel(
                CHANNEL_TUGAS_ID,
                "Notifikasi Tugas",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifikasi pengingat tugas"
            }

            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(jadwalChannel)
            manager.createNotificationChannel(tugasChannel)
        }
    }
}
