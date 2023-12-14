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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.mashup.twotoo.presenter.ui.rememberTwoTooAppState
import com.mashup.twotoo.presenter.util.extractScreenName
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

    private lateinit var firebaseAnalytics: FirebaseAnalytics

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

        firebaseAnalytics = Firebase.analytics
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
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = true,
                    )
                    checkPermission(context, launcher)
                }

                val twoTooAppState = rememberTwoTooAppState()

                val currentDestination = twoTooAppState.currentDestination
                LaunchedEffect(currentDestination) {
                    val params = Bundle()
                    if (currentDestination?.route == null) {
                        return@LaunchedEffect
                    }
                    val screenRoute = currentDestination.route?.extractScreenName() ?: return@LaunchedEffect
                    Log.e("Destination", "screen route = $screenRoute")
                    params.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenRoute)
                    params.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenRoute)
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)
                }
                TwoTooApp(
                    appState = twoTooAppState,
                )
                if (needToUpdateVersion(context)) {
                    TwoTooDialog(
                        content = DialogContent.createVersionUpdateConfirmDialogContent(
                            positiveAction = {
                                launchGooglePlayStore(context)
                            },
                        ),
                    )
                }

            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
        const val REMOTE_KEY_APP_VERSION = "latest_version"
    }
}
private fun remoteConfigInit() {
    val remoteConfig = Firebase.remoteConfig
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }
    remoteConfig.setConfigSettingsAsync(configSettings)

    remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
        val latestVersion = remoteConfig.getString(MainActivity.REMOTE_KEY_APP_VERSION)
        if (task.isSuccessful) {
            Log.i(MainActivity.TAG, "remoteConfigInit - Success= $latestVersion")
        } else {
            Log.i(MainActivity.TAG, "remoteConfigInit - Fail= $latestVersion")
        }
    }

    remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
        override fun onUpdate(configUpdate: ConfigUpdate) {
            Log.d(MainActivity.TAG, "Updated keys: " + configUpdate.updatedKeys)
            if (configUpdate.updatedKeys.contains(MainActivity.REMOTE_KEY_APP_VERSION)) {
                remoteConfig.activate()
                    .addOnCompleteListener { task ->
                        val latestVersion = remoteConfig.getString(MainActivity.REMOTE_KEY_APP_VERSION)
                        if (task.isSuccessful) {
                            Log.i(
                                MainActivity.TAG,
                                "remoteConfigInit update - Success= $latestVersion",
                            )
                        } else {
                            Log.i(
                                MainActivity.TAG,
                                "remoteConfigInit update - Fail= $latestVersion",
                            )
                        }
                    }
            }
        }

        override fun onError(error: FirebaseRemoteConfigException) {
            Log.w(MainActivity.TAG, "Config update error with code: " + error.code, error)
        }
    })
}

private fun needToUpdateVersion(context: Context): Boolean {
    val latestVersion = Firebase.remoteConfig.getString(MainActivity.REMOTE_KEY_APP_VERSION)
    val currentAppVersion = context.packageManager.getPackageInfoCompat(context.packageName).versionName
    Log.i(MainActivity.TAG, "needToUpdateVersion: currentAppVersion= $currentAppVersion")
    return latestVersion.isNotEmpty() && latestVersion != currentAppVersion
}

private fun launchGooglePlayStore(context: Context) {
    if (checkGooglePlayServices(context)) {
        val uri = "market://details?id=${context.packageName}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context.startActivity(intent)
    }
}

private fun checkGooglePlayServices(context: Context): Boolean {
    val googleApiAvailability = GoogleApiAvailability.getInstance()
    val status = googleApiAvailability.isGooglePlayServicesAvailable(context)
    if (status != ConnectionResult.SUCCESS) {
        Toast.makeText(context, context.getString(R.string.version_update_dialog_fail_toast), Toast.LENGTH_SHORT).show()
        return false
    }
    return true
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
