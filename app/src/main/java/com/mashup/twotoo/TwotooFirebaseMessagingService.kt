package com.mashup.twotoo

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mashup.twotoo.presenter.MainActivity

class TwotooFirebaseMessagingService : FirebaseMessagingService() {

    private val notificationManager by lazy {
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH,
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: Refreshed token= $token")
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "onMessageReceived: From= ${remoteMessage.from}")

        // TODO 데이터 처리 로직 생기면 구현
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "onMessageReceived: Message data payload= ${remoteMessage.data}")

            val title = remoteMessage.data.getOrDefault("title", "")
            val message = remoteMessage.data.getOrDefault("body", "")
            val challengeNo = remoteMessage.data.getOrDefault("challengeNo", "0")
            val commitNo = remoteMessage.data.getOrDefault("commitNo", "0")

            sendNotification(title, message, challengeNo.toInt(), commitNo.toInt())
        }
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: 앱서버에 토큰 등록하는 로직 구현
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }

    private fun sendNotification(title: String, messageBody: String, challengeNo: Int, commitNo: Int) {
        // Since android Oreo notification channel is needed.
        Log.i(TAG, "sendNotification: challengeNo= $challengeNo, commitNo=$commitNo")
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("challengeNo", challengeNo)
            putExtra("commitNo", commitNo)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val requestCode = 0
        val pendingIntent = PendingIntent.getActivity(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_app_icon)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH).build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "sendNotification: message Fail, no permission")
            return
        }
        notificationManager.notify(0, notificationBuilder)
    }

    companion object {
        private const val TAG = "TwotooFirebaseMessagingService"
        private const val CHANNEL_ID = "Twotoo"
    }
}
