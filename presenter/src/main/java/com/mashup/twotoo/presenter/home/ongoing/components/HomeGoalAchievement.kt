package com.mashup.twotoo.presenter.home.ongoing.components

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
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeGoalAchieveUiModel
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER

@Composable
fun HomeGoalAchievement(
    homeGoalAchievePartnerAndMeUiModel: HomeGoalAchievePartnerAndMeUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        GoalAchievementRow(
            modifier = Modifier.fillMaxWidth(),
            homeGoalAchieveUiModel = homeGoalAchievePartnerAndMeUiModel.partner,
        )
        Spacer(
            modifier = Modifier.height(4.dp),
        )
        GoalAchievementRow(
            modifier = Modifier.fillMaxWidth(),
            homeGoalAchieveUiModel = homeGoalAchievePartnerAndMeUiModel.me,
        )
    }
}

@Composable
fun GoalAchievementRow(
    homeGoalAchieveUiModel: HomeGoalAchieveUiModel,
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
            text = homeGoalAchieveUiModel.name,
            color = when (homeGoalAchieveUiModel.type) {
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
                progress = homeGoalAchieveUiModel.progress,
                color = TwoTooTheme.color.mainPink,
                trackColor = Color(0xFFF5DBD0),
                strokeCap = StrokeCap.Round,
            )
            Spacer(modifier = Modifier.width(11.dp))
            Text(
                modifier = Modifier,
                text = "${(homeGoalAchieveUiModel.progress * 100).toInt()}%",
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
            homeGoalAchieveUiModel = HomeGoalAchieveUiModel.default,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeGoalAchievement() {
    TwoTooTheme {
        HomeGoalAchievement(
            modifier = Modifier.width(203.dp).height(59.dp),
            homeGoalAchievePartnerAndMeUiModel = HomeGoalAchievePartnerAndMeUiModel.default,
        )
    }
}
