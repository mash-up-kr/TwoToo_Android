package com.mashup.twotoo.presenter.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * @Created by 김현국 2023/06/27
 */

@Suppress("UNCHECKED_CAST")
@Composable
fun <T : Any> componentProvider(): T {
    return LocalContext.current.applicationContext as T
}
