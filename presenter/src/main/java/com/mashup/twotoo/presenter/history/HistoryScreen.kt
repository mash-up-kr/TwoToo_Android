package com.mashup.twotoo.presenter.history

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel

@Composable
fun HistoryScreen(historyItemUiModels: List<HistoryItemUiModel> = listOf()) {
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
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues = it)) {
            ChallengeInfo(
                "24",
                "30분 이상 운동하기",
                "운동 사진으로 인증하기\n인증 실패하는지 확인",
            )
            Spacer(modifier = Modifier.height(37.dp))
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
        HistoryScreen(HistoryItemUiModel.generateDummyHistoryItemsToPreView())
    }
}

@Preview(name = "비어있을때")
@Composable
private fun PreviewHistoryScreenEmpty() {
    TwoTooTheme {
        HistoryScreen(HistoryItemUiModel.generateDummyEmptyHistoryItemsToPreView())
    }
}