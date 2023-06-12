package com.mashup.twotoo.presenter.garden

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.mashup.twotoo.presenter.R

@Composable
fun GardenRoute(
    modifier: Modifier = Modifier,
) {
    GardenScreen(modifier = modifier.testTag(stringResource(id = R.string.garden)))
}

@Composable
fun GardenScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text("Garden Screen")
    }
}
