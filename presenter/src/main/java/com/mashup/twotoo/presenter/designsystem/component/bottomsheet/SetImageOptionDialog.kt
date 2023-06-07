package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

/**
 * @Created by 김현국 2023/06/07
 */

@Composable
fun SetImageOptionDialog(
    onDismissRequest: () -> Unit,
    onClickCameraButton: () -> Unit,
    onClickAlbumButton: () -> Unit,
    onClickDismissButton: () -> Unit,
    properties: DialogProperties = DialogProperties(),

) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 34.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextContainer(
                    modifier = Modifier.fillMaxWidth()
                        .height(55.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 7.dp, topEnd = 7.dp),
                        ).clickable {
                            onClickCameraButton()
                        },
                    text = {
                        Text(
                            text = "사진 촬영하기",
                            textAlign = TextAlign.Center,
                            style = TwoTooTheme.typography.headLineNormal18,

                        )
                    },
                )
                Divider()
                TextContainer(
                    modifier = Modifier.fillMaxWidth()
                        .height(55.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(bottomStart = 7.dp, bottomEnd = 7.dp),
                        ).clickable {
                            onClickAlbumButton()
                        },
                    text = {
                        Text(
                            text = "앨범에서 가져오기",
                            textAlign = TextAlign.Center,
                            style = TwoTooTheme.typography.headLineNormal18,
                        )
                    },
                )

                Spacer(modifier = Modifier.height(8.dp))
                TextContainer(
                    modifier = Modifier.fillMaxWidth()
                        .height(55.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(7.dp),
                        ).clickable {
                            onClickDismissButton()
                        },
                    text = {
                        Text(
                            text = "취소",
                            textAlign = TextAlign.Center,
                            style = TwoTooTheme.typography.headLineNormal18,

                        )
                    },
                )
            }
        }
    }
}

@Composable
fun TextContainer(
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        text()
    }
}

@Composable
private fun Scrim(
    color: Color,
    onDismissRequest: () -> Unit,
    visible: Boolean,
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec(),
            label = "배경",
        )
        val dismissSheet = if (visible) {
            Modifier
                .pointerInput(onDismissRequest) {
                    detectTapGestures {
                        onDismissRequest()
                    }
                }.clearAndSetSemantics {}
        } else {
            Modifier
        }
        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissSheet),
        ) {
            drawRect(color = color, alpha = alpha)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScrim() {
    TwoTooTheme {
        SetImageOptionDialog(
            {},
            onClickAlbumButton = {},
            onClickCameraButton = {},
            onClickDismissButton = {},
        )
    }
}
