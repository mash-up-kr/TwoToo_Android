package com.mashup.twotoo.presenter.history.datail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel

@Composable
fun HistoryDetailScreen(historyDetailInfoUiModel: HistoryDetailInfoUiModel) {
    Scaffold(
        topBar = {
            TwoTooMainToolbar(title = stringResource(id = R.string.historyDetailTitle, historyDetailInfoUiModel.ownerNickName))
        },
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides TwoTooTheme.color.mainBrown,
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .padding(horizontal = 24.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = historyDetailInfoUiModel.createdDate,
                        style = TwoTooTheme.typography.headLineNormal24,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                    )
                }
                TwoTooImageView(
                    model = historyDetailInfoUiModel.infoUiModel.photoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(327.dp)
                        .padding(vertical = 24.dp)
                        .clip(TwoTooTheme.shape.extraSmall),
                )
                Text(
                    text = historyDetailInfoUiModel.challengeName,
                    style = TwoTooTheme.typography.headLineNormal24,
                )
                Text(
                    text = historyDetailInfoUiModel.impressions,
                    modifier = Modifier.padding(top = 20.dp),
                    style = TwoTooTheme.typography.headLineNormal18,
                )
                Text(
                    text = stringResource(
                        id = R.string.historyDetailCreatedTime,
                        historyDetailInfoUiModel.infoUiModel.createdTime,
                    ),
                    modifier = Modifier.padding(top = 20.dp),
                    style = TwoTooTheme.typography.headLineNormal18,
                    color = TwoTooTheme.color.mainPink,
                )

                if (historyDetailInfoUiModel.complimentFromPartner.isNotEmpty()) {
                    Text(
                        text = stringResource(
                            id = R.string.complimentFromPartner,
                            historyDetailInfoUiModel.partnerNickname,
                        ),
                        modifier = Modifier.padding(top = 33.dp),
                        style = TwoTooTheme.typography.bodyNormal16,
                        color = TwotooPink,
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(46.dp)
                            .clip(TwoTooTheme.shape.extraSmall)
                            .background(TwoTooTheme.color.mainWhite),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = historyDetailInfoUiModel.complimentFromPartner,
                            style = TwoTooTheme.typography.bodyNormal16,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryDetailScreen() {
    HistoryDetailScreen(
        HistoryDetailInfoUiModel.getHistoryDetailInfoUiModelToPreview()
            .copy(complimentFromPartner = "앞으로 더 화이팅 이야!"),
    )
}

@Preview("파트너가 칭찬 하지 않았을 때")
@Composable
private fun PreviewHistoryDetailScreeWithoutComplimentFromPartner() {
    HistoryDetailScreen(HistoryDetailInfoUiModel.getHistoryDetailInfoUiModelToPreview())
}
