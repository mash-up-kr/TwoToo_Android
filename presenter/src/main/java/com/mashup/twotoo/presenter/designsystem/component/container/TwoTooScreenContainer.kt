package com.mashup.twotoo.presenter.designsystem.component.container

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView

@Composable
fun TwoTooScreenContainer(
    screen: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImageId: Int? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        if (backgroundImageId != null) {
            TwoTooImageView(
                modifier = Modifier.fillMaxSize(),
                model = backgroundImageId,
                contentScale = ContentScale.Crop,
                previewPlaceholder = backgroundImageId,
            )
        }
        screen()
    }
}
