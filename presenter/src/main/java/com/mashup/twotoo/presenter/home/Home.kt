package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @Created by 김현국 2023/05/16
 * @Time 5:24 PM
 */

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Home Screen")
    }
}
