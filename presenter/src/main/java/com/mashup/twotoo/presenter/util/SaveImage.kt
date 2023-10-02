package com.mashup.twotoo.presenter.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun saveBitmapToStorage(
    context: Context,
    bitmap: Bitmap,
    title: String,
    onSuccessToSave: () -> Unit,
    onFailToSave: () -> Unit,
    mimeType: String = "image/jpeg",
    directory: String = Environment.DIRECTORY_PICTURES,
    mediaContentUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
) {
    val imageOutStream: OutputStream?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, title)
            put(MediaStore.Images.Media.MIME_TYPE, mimeType)
            put(MediaStore.Images.Media.RELATIVE_PATH, directory)
        }

        context.contentResolver.run {
            val uri = this.insert(mediaContentUri, values) ?: run {
                onFailToSave()
                return
            }
            imageOutStream = openOutputStream(uri) ?: run {
                onFailToSave()
                return
            }
        }
    } else {
        val imagePath = Environment.getExternalStoragePublicDirectory(directory).absolutePath
        val image = File(imagePath, title)
        imageOutStream = FileOutputStream(image)
    }
    imageOutStream.use { bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it) }.run {
        onSuccessToSave()
    }
}
