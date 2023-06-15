package com.mashup.twotoo.presenter.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.mashup.twotoo.presenter.R

@Composable
fun UserRoute(
    modifier: Modifier = Modifier,
) {
    UserScreen(modifier = modifier.testTag(stringResource(id = R.string.user)))
}

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text("User Screen")
    }
}
