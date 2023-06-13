package com.mashup.twotoo.presenter.designsystem.component.container

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun TwoTooScreenContainer(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImageId: Int? = null,
    screen: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().then(modifier),
    ) {
        if (backgroundImageId != null) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = backgroundImageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        screen()
    }
}
