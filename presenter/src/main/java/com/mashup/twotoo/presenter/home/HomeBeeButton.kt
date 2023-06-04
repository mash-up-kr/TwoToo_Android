package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView

@Composable
fun BeeButton(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(
            color = Color(0xFFFFE6AF),
            shape = CircleShape,
        ).clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        TwoTooImageView(
            modifier = Modifier.size(51.52.dp),
            model = R.drawable.image_bee,
        )
    }
}


