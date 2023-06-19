package com.mashup.twotoo.presenter.designsystem.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.ThemeColor
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun TwoTooScreenContainer(
    screen: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    currentThemeColor: ThemeColor = ThemeColor.Default,
) {
    Box(
        modifier = Modifier.fillMaxSize().then(modifier),
    ) {
        TwoTooImageView(
            modifier = Modifier.fillMaxSize(),
            model = {
                when (currentThemeColor) {
                    ThemeColor.Default -> {
                        R.drawable.image_background
                    }
                }
            },
            previewPlaceholder = R.drawable.image_background,
            contentScale = ContentScale.Crop,
            failurePlaceHolder = {
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        color =
                        when (currentThemeColor) {
                            ThemeColor.Default -> {
                                TwoTooTheme.color.backgroundYellow
                            }
                        },
                    ),
                ) {}
            },
        )

        screen()
    }
}
