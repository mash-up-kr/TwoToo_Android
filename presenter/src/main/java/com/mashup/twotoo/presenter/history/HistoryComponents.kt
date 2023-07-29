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
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.*
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel
import java.util.*

@Composable
fun OwnerNickNames(ownerNickNamesUiModel: OwnerNickNamesUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 67.dp)
            .padding(top = 37.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CardText(text = ownerNickNamesUiModel.partnerName)
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
        Row(modifier = Modifier.padding(top = 9.dp), verticalAlignment = Alignment.CenterVertically) {
            CardText(
                text = if (challengeInfoUiModel.day == 0) {
                    stringResource(id = R.string.finish)
                } else {
                    stringResource(
                        id = R.string.DDay,
                        challengeInfoUiModel.day,
                    )
                },
                fontColor = TwoTooTheme.color.gray500,
                shape = TwoTooRound4,
                textStyle = TwoTooTheme.typography.bodyNormal16,
            )
            Text(
                modifier = Modifier.padding(start = 11.dp),
                text = challengeInfoUiModel.name,
                // Todo line height 테마로 적용해야함
                style = TwoTooTheme.typography.headLineNormal24.copy(lineHeight = 22.sp),
                color = TwoTooTheme.color.mainBrown,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = challengeInfoUiModel.detail,
            style = TwoTooTheme.typography.bodyNormal16.copy(lineHeight = 26.sp),
            color = TwoTooTheme.color.gray500,
        )
    }
}

@Composable
fun HistoryItems(items: List<HistoryItemUiModel>, navigateToHistoryDetail: (Int) -> Unit) {
    LazyColumn {
        items(items) { item ->
            HistoryItem(item, navigateToHistoryDetail)
        }
    }
}

@Composable
private fun HistoryItem(historyItemUiModel: HistoryItemUiModel, navigateToHistoryDetail: (Int) -> Unit) {
    // Todo 센터고정에서 가로 24dp 패딩으로 변경해야함.(디자인 팀에게 dp사이즈 수정 요청)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HistoryInfo(
            historyInfoUiModel = historyItemUiModel.partnerInfo,
            isAuthenticateExpired = historyItemUiModel.isAuthenticateExpired,
            isMyHistoryInfo = false,
            navigateToHistoryDetail = navigateToHistoryDetail,
        )
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
                text = HistoryItemUiModel.toSortDate(historyItemUiModel.createDate), // ex to 4/2 (from 2022-04-02)
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwotooPink,
            )
        }
        HistoryInfo(
            historyInfoUiModel = historyItemUiModel.myInfo,
            isAuthenticateExpired = historyItemUiModel.isAuthenticateExpired,
            isMyHistoryInfo = true,
            navigateToHistoryDetail = navigateToHistoryDetail,
        )
    }
}

@Composable
private fun HistoryInfo(historyInfoUiModel: HistoryInfoUiModel, isAuthenticateExpired: Boolean, isMyHistoryInfo: Boolean, navigateToHistoryDetail: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .size(127.dp)
            .clip(TwoTooTheme.shape.large)
            .background(TwoTooTheme.color.mainWhite)
            .clickable {
                if (historyInfoUiModel.photoUrl.isNotEmpty()) {
                    navigateToHistoryDetail(historyInfoUiModel.commitNo)
                }
            },
    ) {
        if (historyInfoUiModel.photoUrl.isEmpty()) {
            if (isAuthenticateExpired) {
                ExpiredHistoryInfo()
            } else {
                EmptyHistoryInfo(isMyHistoryInfo)
            }
        } else {
            TwoTooImageView(
                model = historyInfoUiModel.photoUrl,
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
private fun BoxScope.ExpiredHistoryInfo() {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TwoTooImageView(
            modifier = Modifier
                .size(width = 79.dp, height = 52.dp)
                .padding(bottom = 9.dp),
            model = R.drawable.img_failed,
            previewPlaceholder = R.drawable.img_failed,
            contentScale = ContentScale.Fit,
        )
        Text(
            text = stringResource(id = R.string.authenticate_expired),
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.gray500,
        )
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
            shape = TwoTooTheme.shape.extraSmall,
        )
    } else {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TwoTooImageView(
                modifier = Modifier.size(width = 63.dp, height = 55.dp),
                model = R.drawable.img_leaf,
                previewPlaceholder = R.drawable.img_leaf,
                contentScale = ContentScale.Fit,
            )
            Text(
                text = stringResource(id = R.string.authenticate_waiting),
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.gray500,
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
    HistoryInfo(
        historyInfoUiModel = HistoryInfoUiModel.default,
        isMyHistoryInfo = true,
        navigateToHistoryDetail = { },
        isAuthenticateExpired = false,
    )
}

@Preview("연인이 히스토리에 인증 안했을 때")
@Composable
private fun PreviewHistoryItemPartnerEmpty() {
    HistoryInfo(
        historyInfoUiModel = HistoryInfoUiModel.empty,
        isMyHistoryInfo = false,
        navigateToHistoryDetail = { },
        isAuthenticateExpired = false,
    )
}

@Preview("인증 실패 화면")
@Composable
private fun PreviewHistoryExpired() {
    Box {
        ExpiredHistoryInfo()
    }
}
