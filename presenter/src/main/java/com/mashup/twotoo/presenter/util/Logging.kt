package com.mashup.twotoo.presenter.util

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

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
}
