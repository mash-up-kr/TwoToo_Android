package com.mashup.twotoo.presenter.garden

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GardenRoute() {
    GardenScreen()
}

@Composable
fun GardenScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Garden Screen")
    }
}
