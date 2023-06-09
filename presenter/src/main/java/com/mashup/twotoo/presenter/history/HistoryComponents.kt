package com.mashup.twotoo.presenter.history

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.*
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel

@Composable
fun OwnerNickNames(ownerNickNamesUiModel: OwnerNickNamesUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 67.dp)
            .padding(top = 37.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CardText(text = ownerNickNamesUiModel.parameterName)
        CardText(text = ownerNickNamesUiModel.myNickName, fontColor = MainPink)
    }
}

@Composable
private fun CardText(
    modifier: Modifier = Modifier,
    text: String,
    backGroundColor: Color = TwoTooTheme.color.mainWhite,
    fontColor: Color = TwoTooTheme.color.mainBrown,
    textStyle: TextStyle = TwoTooTheme.typography.bodyNormal14,
    shape: CornerBasedShape = TwoTooTheme.shape.medium,
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = backGroundColor),
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
fun ChallengeInfo(challengeInfoUiModel: ChallengeInfoUiModel) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CardText(
                text = "D-${challengeInfoUiModel.day}",
                fontColor = TwoTooTheme.color.gray500,
                shape = TwoTooRound4,
                textStyle = TwoTooTheme.typography.bodyNormal16,
            )
            Text(
                modifier = Modifier.padding(start = 11.dp),
                text = challengeInfoUiModel.name,
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = challengeInfoUiModel.detail,
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.gray500,
        )
    }
}

@Composable
fun HistoryItems(items: List<HistoryItemUiModel>, navigateToHistoryDetail: () -> Unit) {
    LazyColumn {
        items(items) { item ->
            HistoryItem(item, navigateToHistoryDetail)
        }
    }
}

@Composable
private fun HistoryItem(historyItemUiModel: HistoryItemUiModel, navigateToHistoryDetail: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HistoryInfo(historyInfoUiModel = historyItemUiModel.partnerInfo, isMyHistoryInfo = false, navigateToHistoryDetail = navigateToHistoryDetail)
        Box(
            modifier = Modifier
                .padding(horizontal = 13.dp)
                .height(45.dp)
                .width(47.dp)
                .clip(TwoTooTheme.shape.small)
                .background(TwoTooTheme.color.mainYellow),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = historyItemUiModel.createDate,
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwotooPink,
            )
        }
        HistoryInfo(historyInfoUiModel = historyItemUiModel.myInfo, isMyHistoryInfo = true, navigateToHistoryDetail = navigateToHistoryDetail)
    }
}

@Composable
private fun HistoryInfo(historyInfoUiModel: HistoryInfoUiModel, isMyHistoryInfo: Boolean, navigateToHistoryDetail: () -> Unit) {
    Box(
        modifier = Modifier
            .size(127.dp)
            .clip(TwoTooTheme.shape.large)
            .background(TwoTooTheme.color.mainWhite).clickable {
                navigateToHistoryDetail()
            },
    ) {
        if (historyInfoUiModel.photoUrl.isEmpty()) {
            EmptyHistoryInfo(isMyHistoryInfo)
        } else {
            TwoTooImageView(
                model = { historyInfoUiModel.photoUrl },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        TwoTooRound10,
                    ),
            )
            Text(
                text = historyInfoUiModel.createdTime,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp),
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.mainWhite,
            )
        }
    }
}

@Composable
private fun BoxScope.EmptyHistoryInfo(isMyHistoryInfo: Boolean) {
    if (isMyHistoryInfo) {
        CardText(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                },
            text = stringResource(id = R.string.authenticate),
            fontColor = TwoTooTheme.color.mainWhite,
            backGroundColor = TwoTooTheme.color.mainBrown,
        )
    } else {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TwoTooImageView(
                modifier = Modifier.size(width = 65.dp, height = 55.dp),
                model = R.drawable.cloud,
                previewPlaceholder = R.drawable.cloud,
                contentScale = ContentScale.Fit,
            )
            Text(
                text = stringResource(id = R.string.authenticate_waiting),
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.gray400,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewOwnerNickNames() {
    OwnerNickNames(OwnerNickNamesUiModel.default)
}

@Preview(showBackground = true)
@Composable
private fun PreviewDottedLine() {
    DottedLine()
}

@Preview
@Composable
private fun PreviewChallengeInfo() {
    ChallengeInfo(ChallengeInfoUiModel.default)
}

@Preview
@Composable
private fun PreviewHistoryItem() {
    HistoryItem(historyItemUiModel = HistoryItemUiModel.default[0], {})
}

@Preview("내 히스토리에 인증 안했을 때")
@Composable
private fun PreviewHistoryItemEmpty() {
    HistoryInfo(HistoryInfoUiModel("", ""), true, {})
}

@Preview("연인이 히스토리에 인증 안했을 때")
@Composable
private fun PreviewHistoryItemPartnerEmpty() {
    HistoryInfo(HistoryInfoUiModel("", ""), false, {})
}
