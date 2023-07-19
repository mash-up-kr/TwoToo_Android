package com.mashup.twotoo.presenter.history

import android.util.Log
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.dialog.DialogButtonContent
import com.mashup.twotoo.presenter.designsystem.component.dialog.DialogContent
import com.mashup.twotoo.presenter.designsystem.component.dialog.TwoTooDialog
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.history.model.*
import com.mashup.twotoo.presenter.home.TwoTooGoalAchievementProgressbar
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HistoryRoute(
    challengeNo: Int,
    historyViewModel: HistoryViewModel,
    onClickBackButton: () -> Unit,
    navigateToHistoryDetail: () -> Unit,
) {
    Log.i("HistoryRoute", "challengeNo = $challengeNo")
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            historyViewModel.getChallengeByUser(challengeNo)
        }
    }

    val state by historyViewModel.collectAsState()

    HistoryScreen(
        isHomeGoalAchievementShow = false,
        onClickBackButton = onClickBackButton,
        navigateToHistoryDetail = navigateToHistoryDetail,
        state = state,
    )
}

@Composable
fun HistoryScreen(
    isHomeGoalAchievementShow: Boolean,
    onClickBackButton: () -> Unit,
    navigateToHistoryDetail: () -> Unit,
    state: HistoryState,
) {
    var showSelectListDialog by remember { mutableStateOf(false) }
    var showChallengeDropDialog by remember { mutableStateOf(false) }
    Box {
        Scaffold(
            topBar = {
                TwoTooBackToolbar(
                    onClickBackIcon = {
                        onClickBackButton()
                    },
                ) {
                    IconButton(onClick = { showSelectListDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more),
                            contentDescription = null,
                        )
                    }
                }
            },
            containerColor = TwoTooTheme.color.backgroundYellow,
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues = it)) {
                ChallengeInfo(
                    state.challengeInfoUiModel,
                )
                if (isHomeGoalAchievementShow) {
                    TwoTooGoalAchievementProgressbar(
                        modifier = Modifier.padding(top = 12.dp, start = 24.dp).width(210.dp)
                            .height(59.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(15.dp)),
                        homeGoalAchievePartnerAndMeUiModel = HomeGoalAchievePartnerAndMeUiModel.default,
                    )
                }
                OwnerNickNames(state.ownerNickNamesUiModel)
                Spacer(modifier = Modifier.height(12.dp))
                Divider(
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().width(1.dp).padding(horizontal = 24.dp),
                )
                Box {
                    DottedLine()
                    HistoryItems(state.historyItemUiModel, navigateToHistoryDetail)
                }
            }
        }
        if (showSelectListDialog) {
            ChallengeDropSelectionDialog(
                dropDialogTextUiModels = listOf(
                    DropDialogTextUiModel(
                        titleId = R.string.challenge_done,
                        buttonAction = {
                            showSelectListDialog = false
                            showChallengeDropDialog = true
                        },
                        color = TwotooPink,
                    ),
                    DropDialogTextUiModel(
                        titleId = R.string.cancel,
                        buttonAction = { showSelectListDialog = false },
                        color = Color.Black,
                    ),
                ),
            )
        }
        if (showChallengeDropDialog) {
            TwoTooDialog(
                content =
                DialogContent(
                    title = R.string.challenge_done,
                    desc = R.string.challengeDoneDescription,
                    image = R.drawable.crying_cloud,
                    buttons = listOf(
                        DialogButtonContent(
                            text = R.string.cancel,
                            action = { showChallengeDropDialog = false },
                        ),
                        DialogButtonContent(
                            text = R.string.done,
                            action = {},
                        ),
                    ),
                ),

            )
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryScreen() {
    TwoTooTheme {
        HistoryScreen(
            isHomeGoalAchievementShow = false,
            onClickBackButton = {},
            state = HistoryState.default,
            navigateToHistoryDetail = {},
        )
    }
}

@Preview(name = "비어있을때")
@Composable
private fun PreviewHistoryScreenEmpty() {
    TwoTooTheme {
        HistoryScreen(
            isHomeGoalAchievementShow = false,
            onClickBackButton = {},
            state = HistoryState.default,
            navigateToHistoryDetail = {},
        )
    }
}

@Preview(name = "프로그래스바가 보이는 화면")
@Composable
private fun PreviewHistoryScreenWithProgressBar() {
    TwoTooTheme {
        HistoryScreen(
            isHomeGoalAchievementShow = true,
            onClickBackButton = {},
            state = HistoryState.default,
            navigateToHistoryDetail = {},
        )
    }
}
