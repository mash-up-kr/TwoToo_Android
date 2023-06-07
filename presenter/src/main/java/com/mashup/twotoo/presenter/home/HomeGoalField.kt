package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink


@Composable
fun HomeGoalField(
    homeGoalFieldData: HomeGoalFieldData,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.padding(vertical = 16.dp).background(
            color = TwoTooTheme.color.mainYellow,
            shape = TwoTooTheme.shape.small,
        ),
    ) {
        val (goalText, dDayText) = createRefs()
        Text(
            modifier = Modifier.constrainAs(goalText) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 48.dp)
            },
            text = homeGoalFieldData.goal,
            color = TwotooPink,
            style = TwoTooTheme.typography.headLineNormal28
        )
        Text(
            modifier = Modifier
                .constrainAs(dDayText) {
                    top.linkTo(goalText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                .padding(vertical = 4.dp, horizontal = 10.dp),
            text = "D-${homeGoalFieldData.dDay}",
            color = TwoTooTheme.color.gray500,
            style = TwoTooTheme.typography.bodyNormal16

        )
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
            homeGoalFieldData = HomeGoalFieldData("30분 이상 운동하기", 24),
        )
    }
}
