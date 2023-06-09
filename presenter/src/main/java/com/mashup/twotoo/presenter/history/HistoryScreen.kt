package com.mashup.twotoo.presenter.history

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel

@Composable
fun HistoryScreen() {
    Scaffold(
        topBar = {
            TwoTooBackToolbar(
                modifier = Modifier.height(56.dp),
                onClickBackIcon = { },
            )
        },
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        val historyItemUiModels: List<HistoryItemUiModel> = generateDummyHistoryItemsToPreView()
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues = it)) {
            ChallengeInfo(
                "24",
                "30분 이상 운동하기",
                "운동 사진으로 인증하기\n 인증 실패하는지 확인",
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
fun PreviewHistoryScreen() {
    TwoTooTheme {
        HistoryScreen()
    }
}

fun generateDummyHistoryItemsToPreView(): List<HistoryItemUiModel> {
    return listOf(
        HistoryItemUiModel(
            partnerInfo = HistoryInfoUiModel(
                "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                "20:35",
            ),
            myInfo = HistoryInfoUiModel(
                "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                "20:35",
            ),
            createDate = "4/10",
        ),
        HistoryItemUiModel(
            partnerInfo = HistoryInfoUiModel(
                "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                "20:35",
            ),
            myInfo = HistoryInfoUiModel(
                "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
                "20:35",
            ),
            createDate = "4/9",
        ),
    )
}
