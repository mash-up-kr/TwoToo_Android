package com.mashup.twotoo.presenter.history

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.MainPink
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel

@Composable
fun OwnerNickNames(partnerNickname: String, myNickname: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 67.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CardText(text = partnerNickname)
        CardText(text = myNickname, MainPink)
    }
}

@Composable
private fun CardText(text: String, fontColor: Color = TwoTooTheme.color.mainBrown, textStyle: TextStyle = TwoTooTheme.typography.bodyNormal14, shape: CornerBasedShape = TwoTooTheme.shape.medium) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = TwoTooTheme.color.mainWhite),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(10.dp),
            color = fontColor,
            style = textStyle,
        )
    }
}

@Preview
@Composable
fun PreviewOwnerNickNames() {
    OwnerNickNames(partnerNickname = "왕자", myNickname = "공주")
}

@Composable
fun DottedLine() {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
    Canvas(Modifier.fillMaxSize()) {
        drawLine(
            color = Color(0xFFF5DBD0),
            start = Offset(size.width / 2f, 0f),
            end = Offset(size.width / 2f, size.height),
            pathEffect = pathEffect,
        )
    }
}

@Composable
fun ChallengeInfo(day: String, name: String, detail: String) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CardText(
                text = "D-$day",
                fontColor = TwoTooTheme.color.gray500,
                shape = RoundedCornerShape(4.dp),
                textStyle = TwoTooTheme.typography.bodyNormal16,
            )
            Text(
                modifier = Modifier.padding(start = 11.dp),
                text = name,
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = detail,
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.gray500,
        )
    }
}

@Composable
fun HistoryItems(items: List<HistoryItemUiModel>) {
    LazyColumn {
        itemsIndexed(items) { index, item ->
            HistoryItem(item)
        }
    }
}

@Composable
private fun HistoryItem(historyItemUiModel: HistoryItemUiModel) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 13.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HistoryInfo(historyInfoUiModel = historyItemUiModel.partnerInfo)
        Box(
            modifier = Modifier
                .padding(horizontal = 13.dp)
                .height(45.dp).width(47.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(TwoTooTheme.color.mainYellow),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = historyItemUiModel.createDate,
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwotooPink,
            )
        }
        HistoryInfo(historyInfoUiModel = historyItemUiModel.myInfo)
    }
}

@Composable
private fun HistoryInfo(historyInfoUiModel: HistoryInfoUiModel) {
    Box(modifier = Modifier.size(127.dp).clip(TwoTooTheme.shape.large)) {
        TwoTooImageView(
            model = { historyInfoUiModel.photoUrl },
            modifier = Modifier.fillMaxSize().clip(
                RoundedCornerShape(10.dp),
            ),
        )
        Text(
            text = historyInfoUiModel.createdTime,
            modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp),
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.mainWhite,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDottedLine() {
    DottedLine()
}

@Preview
@Composable
fun PreviewChallengeInfo() {
    ChallengeInfo(day = "24", name = "30분 이상 운동하기", detail = "운동 사진으로 인증하기\n인증 실패하는지 확인")
}

@Preview
@Composable
private fun PreviewHistoryItem() {
    val historyItemUiModel = HistoryItemUiModel(
        partnerInfo = HistoryInfoUiModel("https://shop.biumfood.com/upload/1623296512image_product044.jpg", "20:35"),
        myInfo = HistoryInfoUiModel("https://shop.biumfood.com/upload/1623296512image_product044.jpg", "20:35"),
        createDate = "4/10",
    )
    HistoryItem(historyItemUiModel = historyItemUiModel)
}
