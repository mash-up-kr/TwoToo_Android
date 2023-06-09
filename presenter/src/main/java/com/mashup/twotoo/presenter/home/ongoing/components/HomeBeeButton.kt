package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun HomeBeeButton(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.size(56.dp).background(
            color = TwoTooTheme.color.mainYellow,
            shape = CircleShape,
        ).clip(CircleShape),
        contentAlignment = Alignment.TopCenter,
    ) {
        TwoTooImageView(
            modifier = Modifier.width(56.dp).height(51.dp),
            model = R.drawable.img_bee,
            previewPlaceholder = R.drawable.img_bee,
        )
    }
}

@Preview
@Composable
private fun PreviewBeeButton() {
    TwoTooTheme {
        HomeBeeButton()
    }
}
