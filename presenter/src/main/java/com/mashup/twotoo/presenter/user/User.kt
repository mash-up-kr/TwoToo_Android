package com.mashup.twotoo.presenter.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserRoute() {
    UserScreen()
}

@Composable
fun UserScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("User Screen")
    }
}
