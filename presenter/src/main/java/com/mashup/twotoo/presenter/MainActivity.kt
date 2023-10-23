package com.mashup.twotoo.presenter

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.twotoo.TwoTooApp

class MainActivity : ComponentActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val challengeNo = intent?.extras?.getInt("challengeNo", 0)
        val commitNo = intent?.extras?.getInt("commitNo", 0)
        Log.i(TAG, "onNewIntent: challnegeNo = $challengeNo, commitNo = $commitNo")
        finish()
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        val challengeNo = intent.getIntExtra("challengeNo", 0)
        val commitNo = intent.getIntExtra("commitNo", 0)
        Log.i(TAG, "onCreate: challengeNo= $challengeNo, commitNo= $commitNo")
        setContent {
            TwoTooTheme {
                val systemUiController = rememberSystemUiController()
                val context = LocalContext.current
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission(),
                ) {}
                SideEffect {
                    // set transparent color so that our image is visible
                    // behind the status bar
                    systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)
                    checkPermission(context, launcher)
                }

                TwoTooApp()
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}

private fun checkPermission(context: Context, launcher: ManagedActivityResultLauncher<String, Boolean>) {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED
    ) {
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
