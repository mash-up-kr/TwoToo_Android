package com.mashup.twotoo.presenter.util

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object Logging {
    private const val TAG = "Logging"

    fun logRegToken() {
        Firebase.messaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result
            val msg = "FCM Registration token: $token"
            Log.d(TAG, "logRegToken: $msg")
        }
    }

    fun getFcmTokenFlow(): Flow<String> = callbackFlow {
        val onCompleteListener = OnCompleteListener<String> { task ->
            if (task.isSuccessful) {
                val token = task.result
                if (token != null) {
                    Log.d(TAG, "getFcmTokenFlow: $token")
                    trySend(token).isSuccess
                }
            } else {
                close(task.exception ?: Exception("Failed to fetch FCM registration token"))
            }
        }

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(onCompleteListener)

        awaitClose {}
    }
}
