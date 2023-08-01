package com.mashup.twotoo.presenter.designsystem.component.scrim

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun TwoTooScrim(
    color: Color,
    onDismissRequest: () -> Unit = {},
    visible: Boolean = true,
) {
    if (color.isSpecified) {
        val dismissSheet = if (visible) {
            Modifier
                .pointerInput(onDismissRequest) {
                    detectTapGestures {
                        onDismissRequest()
                    }
                }
                .clearAndSetSemantics {}
        } else {
            Modifier
        }
        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissSheet),
        ) {
            drawRect(color = color, alpha = 0.4f)
        }
    }
}

@Preview
@Composable
private fun PreviewTwoTooScrim() {
    TwoTooTheme {
        TwoTooScrim(color = TwoTooTheme.color.gray600)
    }
}
