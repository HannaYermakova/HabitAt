package by.aermakova.habitat.model.useCase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import by.aermakova.habitat.R
import by.aermakova.habitat.view.app.AppActivity


class AlarmReceiver : BroadcastReceiver() {
    private var context: Context? = null
    private var manager: NotificationManager? = null

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context
        if (intent.extras != null) {
            val habitTitle = intent.extras!!.getString(HABIT_TITLE_TAG)
            createNotification(habitTitle)
        }
    }

    private fun createNotification(habitTitle: String?) {
        val channelId = context!!.resources.getString(R.string.notification_channel)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(context!!.applicationContext, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(habitTitle)
                .setContentText(context!!.getString(R.string.notification_text))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setShowWhen(true)
        val pendingIntent = openActivity()
        notificationBuilder.setContentIntent(pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (manager == null) {
                manager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            if (manager != null && manager!!.getNotificationChannel(channelId) == null) {
                val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH)
                manager!!.createNotificationChannel(channel)
            }

            if (manager != null) manager!!.notify(0, notificationBuilder.build())
        }
    }

    private fun openActivity(): PendingIntent {
        val openActivity = Intent(context, AppActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntentWithParentStack(openActivity)
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    companion object {
        private const val HABIT_TITLE_TAG = "habit_title"
    }
}