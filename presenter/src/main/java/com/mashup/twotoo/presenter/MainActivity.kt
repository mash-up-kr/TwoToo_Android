package com.mashup.twotoo.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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
    }
}
