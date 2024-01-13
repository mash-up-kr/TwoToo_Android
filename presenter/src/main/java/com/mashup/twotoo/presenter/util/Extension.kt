package com.mashup.twotoo.presenter.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.mashup.twotoo.presenter.designsystem.component.dialog.getImageUri

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION")
        getPackageInfo(packageName, flags)
    }

fun Context.shareImage(bitmap: ImageBitmap?, error: Throwable?, ) {
    if (bitmap != null) {
        val androidBitmap = bitmap.asAndroidBitmap()
        val path = getImageUri(this, androidBitmap)

        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"

        share.putExtra(Intent.EXTRA_STREAM, path)
        this.startActivity(Intent.createChooser(share, "Select"))
    }
    if (error != null) {
        Log.e("Error", error.message ?: "")
    }
}