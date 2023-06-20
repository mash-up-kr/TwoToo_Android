package com.mashup.twotoo.presenter.util.permission

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

/**
 * @Created by 김현국 2023/06/20
 */

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FeatureThatRequiresCameraPermission() {
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA,
    )

    val context = LocalContext.current
    if (cameraPermissionState.status.isGranted) {
        Toast.makeText(context, "Camera permission Granted", Toast.LENGTH_SHORT).show()
    } else {
        cameraPermissionState.launchPermissionRequest()
//        Column {
//            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
//                // If the user has denied the permission but the rationale can be shown,
//                // then gently explain why the app requires this permission
//                "The camera is important for this app. Please grant the permission."
//            } else {
//                // If it's the first time the user lands on this feature, or the user
//                // doesn't want to be asked again for this permission, explain that the
//                // permission is required
//                "Camera permission required for this feature to be available. " +
//                    "Please grant the permission"
//            }
//            Text(textToShow)
//            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
//                Text("Request permission")
//            }
//        }
    }
}
