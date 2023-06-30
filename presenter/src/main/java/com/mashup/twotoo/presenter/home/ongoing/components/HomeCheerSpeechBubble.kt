package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.UserType

/**
 * @Created by 김현국 2023/06/30
 */

@Composable
fun HomeCheerSpeechBubble(
    userType: UserType,
    cheerText: String,
    modifier: Modifier = Modifier,
) {
    val partnerColor = TwoTooTheme.color.mainLightPink
    val meColor = TwoTooTheme.color.mainYellow
    if (cheerText.isEmpty()) {

    } else {
        ConstraintLayout(modifier) {
            val (bubble, text) = createRefs()
            Canvas(
                modifier.constrainAs(bubble) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            ) {
                drawRoundRect(
                    color = if (userType == UserType.PARTNER) {
                        partnerColor
                    } else {
                        meColor
                    },
                    size = Size(width = this.size.width, height = if (cheerText.isOneLine())36.dp.toPx() else 64.dp.toPx()),
                    cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                )
                val trianglePath = Path().apply {
                    when (userType) {
                        UserType.PARTNER -> {
                            moveTo(
                                x = 24.dp.toPx(),
                                y = if (cheerText.isOneLine()) {
                                    24.dp.toPx()
                                } else {
                                    52.dp.toPx()
                                },
                            )
                        }
                        UserType.ME -> {
                            moveTo(
                                x = 100.dp.toPx(),
                                y = if (cheerText.isOneLine()) {
                                    24.dp.toPx()
                                } else {
                                    52.dp.toPx()
                                },
                            )
                        }
                    }

                    when (userType) {
                        UserType.PARTNER -> {
                            if (cheerText.isOneLine()) {
                                lineTo(24.dp.toPx(), 24.dp.toPx()) // 오른쪽 위
                                lineTo(48.dp.toPx(), 24.dp.toPx())
                                lineTo(36.dp.toPx(), 48.dp.toPx())
                            } else {
                                lineTo(24.dp.toPx(), 52.dp.toPx()) // 오른쪽 위
                                lineTo(48.dp.toPx(), 52.dp.toPx())
                                lineTo(36.dp.toPx(), 76.dp.toPx())
                            }
                        }
                        UserType.ME -> {
                            if (cheerText.isOneLine()) {
                                lineTo(100.dp.toPx(), 24.dp.toPx()) // 오른쪽 위
                                lineTo(124.dp.toPx(), 24.dp.toPx())
                                lineTo(112.dp.toPx(), 48.dp.toPx())
                            } else {
                                lineTo(100.dp.toPx(), 52.dp.toPx()) // 오른쪽 위
                                lineTo(124.dp.toPx(), 52.dp.toPx())
                                lineTo(112.dp.toPx(), 76.dp.toPx())
                            }
                        }
                    }
                    close()
                }

                drawIntoCanvas { canvas ->
                    canvas.drawOutline(
                        outline = Outline.Generic(trianglePath),
                        paint = Paint().apply {
                            color = if (userType == UserType.PARTNER) {
                                partnerColor
                            } else {
                                meColor
                            }
                            pathEffect = PathEffect.cornerPathEffect(2.dp.toPx())
                        },
                    )
                }
            }

            val barrier = createTopBarrier(
                bubble,
                margin =
                if (cheerText.isOneLine()) {
                    36.dp
                } else {
                    64.dp
                },
            )
            Text(
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(
                        parent.top,
                        margin = 10.dp,
                    )
                    bottom.linkTo(barrier, margin = 10.dp)
                }.padding(horizontal = 15.dp).fillMaxWidth(),
                text = cheerText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

fun String.isOneLine(): Boolean {
    return this.length <= 10
}

@Preview
@Composable
private fun HomeCheerSpeechBubblePartnerOneLinePreview() {
    TwoTooTheme {
        HomeCheerSpeechBubble(
            modifier = Modifier.width(150.dp),
            userType = UserType.PARTNER,
            cheerText = "안녕하세요안녕하세요",
        )
    }
}

@Preview
@Composable
private fun HomeCheerSpeechBubblePartnerTwoLinePreview() {
    TwoTooTheme {
        HomeCheerSpeechBubble(
            modifier = Modifier.width(150.dp),
            userType = UserType.PARTNER,
            cheerText = "안녕하세요안녕하세요안녕하세요안녕하세요",
        )
    }
}

@Preview
@Composable
private fun HomeCheerSpeechBubbleMeOneLinePreview() {
    TwoTooTheme {
        HomeCheerSpeechBubble(
            modifier = Modifier.width(150.dp).height(48.dp),
            userType = UserType.ME,
            cheerText = "안녕하세요안녕하세요",
        )
    }
}

@Preview
@Composable
private fun HomeCheerSpeechBubbleMeTwoLinePreview() {
    TwoTooTheme {
        HomeCheerSpeechBubble(
            modifier = Modifier.width(150.dp).height(48.dp),
            userType = UserType.ME,
            cheerText = "안녕하세요안녕하세요안녕하세요안녕하세요",
        )
    }
}
