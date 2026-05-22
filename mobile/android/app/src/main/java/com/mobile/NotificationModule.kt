package com.mobile;

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class NotificationModule(context: ReactApplicationContext?) : ReactContextBaseJavaModule(context) {
  companion object {
    private const val CHANNEL_ID = "test_channel"
    private const val CHANNEL_NAME = "TestChannel"
    private const val NOTIFICATION_ID = 1234
    private const val PERMISSION_REQUEST_CODE = 5678
  }

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = reactApplicationContext.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun getName(): String {
        return "Notification"
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    @ReactMethod
    fun showNotification(title: String?, content: String?) {
        val notification = NotificationCompat.Builder(reactApplicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_dialog_alert)
            .setContentTitle(title)
            .setContentText(content)
            .setAutoCancel(true)
            .build()
        NotificationManagerCompat.from(reactApplicationContext).notify(NOTIFICATION_ID, notification)
    }

    @ReactMethod
    fun requestPermissions() {

        val hasPermission = ContextCompat.checkSelfPermission(reactApplicationContext, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            currentActivity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }
}