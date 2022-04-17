package app.te.protein_chef.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.presentation.home.HomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService() :
  FirebaseMessagingService() {
  override fun onMessageReceived(remoteMessage: RemoteMessage) {
    sendNotification(remoteMessage.data)
  }

  override fun onNewToken(token: String) {
    Log.e("onNewToken", "onNewToken: " + token)
  }

  private fun sendNotification(messageBody: MutableMap<String, String>) {
    val bundle = Bundle()
    bundle.putBoolean(Constants.NOTIFICATION, true)
    val pendingIntent = NavDeepLinkBuilder(this)
      .setComponentName(HomeActivity::class.java)
      .setGraph(R.navigation.nav_home)
      .setDestination(R.id.notificationsFragment)
      .setArguments(bundle)
      .createPendingIntent()

    val channelId = "channelId"
    val defaultSoundUri: Uri = Uri.parse(
      ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.notify_default
    )
    val r: Ringtone = RingtoneManager.getRingtone(applicationContext, defaultSoundUri)
    r.play()
    val notificationBuilder = NotificationCompat.Builder(this, channelId)
      .setSmallIcon(R.mipmap.ic_launcher_release)
      .setPriority(NotificationCompat.PRIORITY_HIGH)
      .setContentTitle(messageBody["title"])
      .setContentText(messageBody["body"])
      .setAutoCancel(true)
      .setSound(defaultSoundUri)
      .setNumber(0)
      .setContentIntent(pendingIntent)

    val notificationManager =
      getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    // Since android Oreo notification channel is needed.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        channelId,
        "Channel human readable title",
        NotificationManager.IMPORTANCE_DEFAULT
      )
      notificationManager.createNotificationChannel(channel)
    }

    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())

  }
}