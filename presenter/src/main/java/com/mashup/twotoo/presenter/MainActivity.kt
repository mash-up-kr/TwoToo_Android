package com.mashup.twotoo.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.twotoo.TwoTooApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            TwoTooTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    // set transparent color so that our image is visible
                    // behind the status bar
                    systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)
                }
                TwoTooApp()
            }
        }
        checkInviteLink(intent)
    }
}

private fun checkInviteLink(intent: Intent) {
    Firebase.dynamicLinks.getDynamicLink(intent).addOnSuccessListener { linkData ->
        var deepLink: Uri? = null
        linkData?.let { data ->
            deepLink = data.link
        }
        deepLink?.let { uri ->
            val nickname = uri.getQueryParameter("nickname")
            val userNo = uri.getQueryParameter("userNo")
            Log.d(TAG, "checkInviteLink: $nickname")
            Log.d(TAG, "checkInviteLink: $userNo")
        }
    }
}
