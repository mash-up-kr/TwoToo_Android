package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.home.UserType.ME
import com.mashup.twotoo.presenter.home.UserType.PARTNER

@Composable
fun HomeGoalAchievement(
    goalAchieveDataList: List<HomeGoalAchieveData>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        GoalAchievementRow(
            modifier = Modifier.fillMaxWidth(),
            homeGoalAchieveData = goalAchieveDataList[0],
        )
        GoalAchievementRow(
            modifier = Modifier.fillMaxWidth(),
            homeGoalAchieveData = goalAchieveDataList[1],
        )
    }
}

@Composable
fun GoalAchievementRow(
    homeGoalAchieveData: HomeGoalAchieveData,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(modifier) {
        val (name, rowArea) = createRefs()
        Text(
            modifier = Modifier.constrainAs(name) {
                end.linkTo(rowArea.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            },
            style = TwoTooTheme.typography.bodyNormal14,
            text = homeGoalAchieveData.name,
            color = when (homeGoalAchieveData.type) {
                ME -> {
                    TwotooPink
                }
                PARTNER -> {
                    Color(0xFF555050)
                }
            },
        )
        Row(
            modifier = Modifier.constrainAs(rowArea) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LinearProgressIndicator(
                modifier = Modifier.width(111.dp).height(9.dp),
                progress = homeGoalAchieveData.progress,
                color = TwoTooTheme.color.mainPink,
                trackColor = Color(0xFFF5DBD0),
                strokeCap = StrokeCap.Round,
            )
            Spacer(modifier = Modifier.width(11.dp))
            Text(
                modifier = Modifier,
                text = "${(homeGoalAchieveData.progress * 100).toInt()}%",
                color = Color(0xFFA4A4A4),
                style = TwoTooTheme.typography.bodyNormal12,
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeGoalAchieveRow() {
    TwoTooTheme() {
        GoalAchievementRow(
            modifier = Modifier,
            homeGoalAchieveData = HomeGoalAchieveData("공주", type = PARTNER, 0.7f),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeGoalAchievement() {
    TwoTooTheme {
        HomeGoalAchievement(
            modifier = Modifier.width(203.dp).height(59.dp),
            goalAchieveDataList = listOf(
                HomeGoalAchieveData("공주", type = PARTNER, 0.7f),
                HomeGoalAchieveData("나", type = ME, 0.6f),
            ),
        )
    }
}
