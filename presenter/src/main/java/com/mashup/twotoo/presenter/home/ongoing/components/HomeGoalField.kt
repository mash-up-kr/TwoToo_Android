package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.home.model.HomeGoalFieldUiModel
import com.mashup.twotoo.presenter.util.AutoResizeText
import com.mashup.twotoo.presenter.util.FontSizeRange

@Composable
fun HomeGoalField(
    homeGoalFieldUiModel: HomeGoalFieldUiModel,
    modifier: Modifier = Modifier,
    onClickHomeGoalField: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animateAlpha by animateFloatAsState(targetValue = if (isPressed) 0.7f else 1f, label = "")

    CompositionLocalProvider(LocalContentAlpha provides animateAlpha) {
        ConstraintLayout(
            modifier = modifier.background(
                color = TwoTooTheme.color.mainYellow.copy(alpha = LocalContentAlpha.current),
                shape = TwoTooTheme.shape.small,
            ).clip(TwoTooTheme.shape.small).clickable(interactionSource, null) {
                onClickHomeGoalField()
            },
        ) {
            val (goalText, dDayText, arrow) = createRefs()
            AutoResizeText(
                modifier = Modifier.constrainAs(goalText) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = homeGoalFieldUiModel.goal,
                color = TwotooPink.copy(alpha = LocalContentAlpha.current),
                style = TwoTooTheme.typography.headLineNormal28,
                fontSizeRange = FontSizeRange(
                    min = 20.sp,
                    max = 28.sp,
                ),
            )
            Text(
                modifier = Modifier
                    .constrainAs(dDayText) {
                        top.linkTo(goalText.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                    }
                    .background(color = Color.White.copy(alpha = LocalContentAlpha.current), shape = RoundedCornerShape(4.dp))
                    .padding(vertical = 4.dp, horizontal = 10.dp),
                text = "D-${homeGoalFieldUiModel.dDay}",
                color = TwoTooTheme.color.gray500.copy(alpha = LocalContentAlpha.current),
                style = TwoTooTheme.typography.bodyNormal16,

            )
            TwoTooImageView(
                modifier = Modifier.size(28.dp).constrainAs(arrow) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, 8.dp)
                },
                model = R.drawable.ic_arrow,
                previewPlaceholder = R.drawable.ic_arrow,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeGoalField() {
    TwoTooTheme() {
        HomeGoalField(
            modifier = Modifier
                .width(327.dp)
                .wrapContentHeight()
                .background(color = Color(0xFFFFE6AF), shape = RoundedCornerShape(15.dp)),
            homeGoalFieldUiModel = HomeGoalFieldUiModel.default,
            onClickHomeGoalField = {},
        )
    }
}
