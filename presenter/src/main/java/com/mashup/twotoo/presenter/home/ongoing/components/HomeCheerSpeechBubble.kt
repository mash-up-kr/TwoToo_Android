package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
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
import androidx.constraintlayout.compose.Dimension
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
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

    ConstraintLayout(
        modifier.width(150.dp).height(
            if (cheerText.isOneLine()) 48.dp else 76.dp,
        ),
    ) {
        val (bubble, text) = createRefs()
        Canvas(
            Modifier.constrainAs(bubble) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                width = Dimension.matchParent
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
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(barrier)
                }.width(120.dp),
            text = cheerText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.mainBrown,
        )
    }
}

@Composable
fun HomeCheerFirstSpeech(
    cheerText: String,
    modifier: Modifier = Modifier,
) {
    val meColor = TwoTooTheme.color.mainYellow

    ConstraintLayout(modifier.then(Modifier.width(150.dp))) {
        val (bubble, textLayout) = createRefs()
        Canvas(
            modifier = Modifier.constrainAs(bubble) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
        ) {
            drawRoundRect(
                color = meColor,
                size = Size(
                    width = this.size.width,
                    height = if (cheerText.isOneLine())36.dp.toPx() else 64.dp.toPx(),
                ),
                cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
            )

            drawCircle(
                color = meColor,
                center = Offset(
                    x = 113.dp.toPx(),
                    y = 36.dp.toPx(),
                ),
                radius = 9.dp.toPx(),
            )

            drawCircle(
                color = meColor,
                center = Offset(
                    x = 122.dp.toPx(),
                    y = 56.dp.toPx(),
                ),
                radius = 6.dp.toPx(),
            )
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

        Row(
            modifier = Modifier.constrainAs(textLayout) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(barrier)
            },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = cheerText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            TwoTooImageView(
                model = R.drawable.ic_cheer_pencil,
                modifier = Modifier.size(16.dp),
                previewPlaceholder = R.drawable.ic_cheer_pencil,
            )
        }
    }
}
fun String.isOneLine(): Boolean {
    return this.length <= 10
}

@Preview
@Composable
private fun HomeCheerSpeechFirstPreview() {
    TwoTooTheme {
        HomeCheerFirstSpeech(
            modifier = Modifier.width(150.dp),
            cheerText = "칭찬 문구 작성하기",
        )
    }
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
            modifier = Modifier.width(150.dp),
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
            modifier = Modifier.width(150.dp),
            userType = UserType.ME,
            cheerText = "안녕하세요안녕하세요안녕하세요안녕하세요",
        )
    }
}
