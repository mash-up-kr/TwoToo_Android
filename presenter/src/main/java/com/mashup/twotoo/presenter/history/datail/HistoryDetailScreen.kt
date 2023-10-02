package com.mashup.twotoo.presenter.history.datail

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.MainWhite
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.history.HistoryViewModel
import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HistoryDetailRoute(
    commitNo: Int,
    historyViewModel: HistoryViewModel,
    onClickBackButton: () -> Unit,
    onClickImage: (String) -> Unit,
) {
    Log.i("HistoryDetailRoute", "commitNo = $commitNo")
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            historyViewModel.updateChallengeDetail(commitNo)
        }
    }
    val state by historyViewModel.collectAsState()
    HistoryDetailScreen(
        historyDetailInfoUiModel = state.historyDetailInfoUiModel,
        onClickBackButton = onClickBackButton,
        onClickImage = onClickImage,
    )
}

@Composable
fun HistoryDetailScreen(
    historyDetailInfoUiModel: HistoryDetailInfoUiModel,
    onClickBackButton: () -> Unit = {},
    onClickImage: (String) -> Unit = {},
) {
    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        TwoTooMainToolbar(
            title = stringResource(
                id = R.string.historyDetailTitle,
                historyDetailInfoUiModel.ownerNickNamesUiModel.myNickName,
            ),
        )

        CompositionLocalProvider(
            LocalContentColor provides TwoTooTheme.color.mainBrown,
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .verticalScroll(scrollableState)
                    .navigationBarsPadding(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = historyDetailInfoUiModel.infoUiModel.createdDate,
                        style = TwoTooTheme.typography.headLineNormal24,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        modifier = Modifier.clickable { onClickBackButton() },
                        contentDescription = null,
                    )
                }
                TwoTooImageView(
                    model = historyDetailInfoUiModel.infoUiModel.photoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .aspectRatio(1f)
                        .clip(TwoTooTheme.shape.extraSmall).clickable {
                            onClickImage(historyDetailInfoUiModel.infoUiModel.photoUrl)
                        },

                )
                Text(
                    text = historyDetailInfoUiModel.challengeName,
                    style = TwoTooTheme.typography.headLineNormal24,
                )
                Text(
                    text = historyDetailInfoUiModel.infoUiModel.text,
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
                    color = TwoTooTheme.color.gray500,
                )

                if (historyDetailInfoUiModel.infoUiModel.partnerComment.isNotEmpty()) {
                    Text(
                        text = stringResource(
                            id = R.string.complimentFromPartner,
                            historyDetailInfoUiModel.ownerNickNamesUiModel.partnerName,
                        ),
                        modifier = Modifier.padding(top = 33.dp),
                        style = TwoTooTheme.typography.bodyNormal16,
                        color = TwotooPink,
                    )

                    Text(
                        text = historyDetailInfoUiModel.infoUiModel.partnerComment,
                        style = TwoTooTheme.typography.bodyNormal16,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .drawBehind {
                                drawRoundRect(
                                    color = MainWhite,
                                    cornerRadius = CornerRadius(10.dp.toPx()),
                                )
                            }
                            .padding(horizontal = 10.dp, vertical = 15.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryDetailScreen() {
    TwoTooTheme {
        val infoUiModel = HistoryDetailInfoUiModel.default.infoUiModel.copy(partnerComment = "앞으로 더 화이팅 이야!")
        HistoryDetailScreen(
            onClickBackButton = {},
            historyDetailInfoUiModel = HistoryDetailInfoUiModel.default.copy(
                infoUiModel = infoUiModel,
            ),
        )
    }
}

@Preview("파트너가 칭찬 하지 않았을 때")
@Composable
private fun PreviewHistoryDetailScreeWithoutComplimentFromPartner() {
    TwoTooTheme {
        HistoryDetailScreen(
            onClickBackButton = {},
            historyDetailInfoUiModel = HistoryDetailInfoUiModel.default,
        )
    }
}
