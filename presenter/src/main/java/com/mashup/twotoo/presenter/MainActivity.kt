package com.mashup.twotoo.presenter

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mashup.twotoo.presenter.designsystem.component.dialog.DialogContent
import com.mashup.twotoo.presenter.designsystem.component.dialog.TwoTooDialog
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.twotoo.TwoTooApp
import com.mashup.twotoo.presenter.util.getPackageInfoCompat

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
        remoteConfigInit()
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
                if (needToUpdateVersion(context)) {
                    TwoTooDialog(
                        content = DialogContent.createVersionUpdateConfirmDialogContent(
                            positiveAction = {
                                launchGooglePlayStore()
                            },
                        ),
                    )
                }
                TwoTooApp()
            }
        }
    }
    private fun remoteConfigInit() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            val latestVersion = remoteConfig.getString(REMOTE_KEY_APP_VERSION)
            if (task.isSuccessful) {
                Log.i(TAG, "remoteConfigInit - Success= $latestVersion")
            } else {
                Log.i(TAG, "remoteConfigInit - Fail= $latestVersion")
            }
        }

        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                Log.d(TAG, "Updated keys: " + configUpdate.updatedKeys)
                if (configUpdate.updatedKeys.contains(REMOTE_KEY_APP_VERSION)) {
                    remoteConfig.activate()
                        .addOnCompleteListener { task ->
                            val latestVersion = remoteConfig.getString(REMOTE_KEY_APP_VERSION)
                            if (task.isSuccessful) {
                                Log.i(
                                    TAG,
                                    "remoteConfigInit update - Success= $latestVersion",
                                )
                            } else {
                                Log.i(
                                    TAG,
                                    "remoteConfigInit update - Fail= $latestVersion",
                                )
                            }
                        }
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Log.w(TAG, "Config update error with code: " + error.code, error)
            }
        })
    }

    private fun needToUpdateVersion(context: Context): Boolean {
        val latestVersion = Firebase.remoteConfig.getString(REMOTE_KEY_APP_VERSION)
        val currentAppVersion = context.packageManager.getPackageInfoCompat(context.packageName).versionName
        Log.i(TAG, "needToUpdateVersion: currentAppVersion= $currentAppVersion")
        return latestVersion.isNotEmpty() && latestVersion != currentAppVersion
    }

    private fun launchGooglePlayStore() {
        if (checkGooglePlayServices()) {
            val uri = "market://details?id=$packageName"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
    }

    private fun checkGooglePlayServices(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(this)
        if (status != ConnectionResult.SUCCESS) {
            Toast.makeText(this, this.getString(R.string.version_update_dialog_fail_toast), Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    companion object {
        const val TAG = "MainActivity"
        const val REMOTE_KEY_APP_VERSION = "latest_version"
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
