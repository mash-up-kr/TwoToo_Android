package com.mashup.twotoo.presenter.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.home.HomeGoalAchievement
import com.mashup.twotoo.presenter.home.model.HomeGoalAchieveData
import com.mashup.twotoo.presenter.home.model.UserType

@Composable
fun HistoryScreen(historyItemUiModels: List<HistoryItemUiModel> = listOf(), isHomeGoalAchievementShow: Boolean) {
    Scaffold(
        topBar = {
            TwoTooBackToolbar(
                onClickBackIcon = { },
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = null,
                    )
                }
            }
        },
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        val isHomeGoalAchievementShow by remember { mutableStateOf(isHomeGoalAchievementShow) }
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues = it)) {
            ChallengeInfo(
                "24",
                "30분 이상 운동하기",
                "운동 사진으로 인증하기\n인증 실패하는지 확인",
            )
            HomeGoalAchievement(
                modifier = Modifier.padding(top = 12.dp, start = 24.dp).width(210.dp).height(59.dp).background(color = Color.White, shape = RoundedCornerShape(15.dp)),
                goalAchieveDataList = listOf(
                    HomeGoalAchieveData(name = "공주", type = UserType.PARTNER, progress = 0.7f),
                    HomeGoalAchieveData(name = "나", type = UserType.ME, progress = 0.6f),
                ),
                isShow = isHomeGoalAchievementShow,
            )
            OwnerNickNames("왕자", "공주")
            Spacer(modifier = Modifier.height(12.dp))
            Divider(
                color = Color.White,
                modifier = Modifier.fillMaxWidth().width(1.dp).padding(horizontal = 24.dp),
            )
            Box {
                DottedLine()
                HistoryItems(historyItemUiModels)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryScreen() {
    TwoTooTheme {
        HistoryScreen(HistoryItemUiModel.generateDummyHistoryItemsToPreView(), isHomeGoalAchievementShow = false)
    }
}

@Preview(name = "비어있을때")
@Composable
private fun PreviewHistoryScreenEmpty() {
    TwoTooTheme {
        HistoryScreen(HistoryItemUiModel.generateDummyEmptyHistoryItemsToPreView(), isHomeGoalAchievementShow = false)
    }
}

@Preview(name = "프로그래스바가 보이는 화면")
@Composable
private fun PreviewHistoryScreenWithProgressBar() {
    TwoTooTheme {
        HistoryScreen(HistoryItemUiModel.generateDummyHistoryItemsToPreView(), isHomeGoalAchievementShow = true)
    }
}
