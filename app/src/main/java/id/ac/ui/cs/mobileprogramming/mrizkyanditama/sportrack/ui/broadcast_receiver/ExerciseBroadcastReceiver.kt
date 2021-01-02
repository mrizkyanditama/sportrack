package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.broadcast_receiver

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import timber.log.Timber

private const val TAG = "ExerciseBroadcastReceiver"

class ExerciseBroadcastReceiver : BroadcastReceiver() {

    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"


    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("Masuk ke broadcast receiver")
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intentReceived = intent
        val pendingIntent = PendingIntent.getActivity(context, 0, intentReceived, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(context, channelId).setContentTitle("Sportrack").setContentText("Kamu telah berolahraga cukup selama 1 menit, tetap semangat!").setSmallIcon(
                R.drawable.ic_launcher_foreground).setLargeIcon(
                BitmapFactory.decodeResource(context.resources, R.drawable
                .ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(12345, builder.build())
    }
}