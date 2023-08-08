package com.mashup.twotoo.presenter.history

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetData
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.TwoTooBottomSheet
import com.mashup.twotoo.presenter.designsystem.component.dialog.DialogContent
import com.mashup.twotoo.presenter.designsystem.component.dialog.TwoTooDialog
import com.mashup.twotoo.presenter.designsystem.component.loading.FlowerLoadingIndicator
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.history.model.DropDialogTextUiModel
import com.mashup.twotoo.presenter.home.TwoTooGoalAchievementProgressbar
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HistoryRoute(
    challengeNo: Int,
    historyViewModel: HistoryViewModel,
    homeGoalAchievePartnerAndMeUiModel: HomeGoalAchievePartnerAndMeUiModel? = null,
    onClickBackButton: () -> Unit,
    navigateToHistoryDetail: (Int) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Log.i(
        "HistoryRoute",
        "lifecylce = ${lifecycleOwner.lifecycle.currentState}, challengeNo = $challengeNo, homeGoalAchievePartnerAndMeUiModel=$homeGoalAchievePartnerAndMeUiModel",
    )

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            Log.i("HistoryRoute", "repeatOnLifecycle state= start")
            historyViewModel.getChallengeByUser(challengeNo)
        }
    }

    val state by historyViewModel.collectAsState()

    HistoryScreen(
        homeGoalAchievePartnerAndMeUiModel = homeGoalAchievePartnerAndMeUiModel,
        onClickBackButton = onClickBackButton,
        navigateToHistoryDetail = navigateToHistoryDetail,
        quiteChallenge = { historyViewModel.quiteChallenge(challengeNo) },
        onClickBottomSheetDataButton = { bottomSheetData ->
            val bt = (bottomSheetData as BottomSheetData.AuthenticateData)
            Log.d("HistoryScreen", "text= ${bt.text} img= ${bt.image}")
            historyViewModel.onClickBottomSheetDataButton(bottomSheetData)
        },
        state = state,
    )
}

@Composable
fun HistoryScreen(
    homeGoalAchievePartnerAndMeUiModel: HomeGoalAchievePartnerAndMeUiModel? = null,
    onClickBackButton: () -> Unit,
    navigateToHistoryDetail: (Int) -> Unit,
    quiteChallenge: () -> Unit,
    onClickBottomSheetDataButton: (BottomSheetData) -> Unit,
    state: HistoryState,
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var bottomSheetType by remember { mutableStateOf<BottomSheetType>(BottomSheetType.Authenticate()) }

    fun onDismiss() {
        isBottomSheetVisible = false
    }

    fun showBottomSheet() {
        isBottomSheetVisible = true
    }

    if (isBottomSheetVisible) {
        TwoTooBottomSheet(
            type = bottomSheetType,
            onDismiss = ::onDismiss,
            onClickButton = {
                onClickBottomSheetDataButton(it)
                isBottomSheetVisible = false
            },

        )
    }
    var showSelectListDialog by remember { mutableStateOf(false) }
    var showChallengeDropDialog by remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),

        ) {
            Spacer(modifier = Modifier.padding(top = 5.dp))
            TwoTooBackToolbar(
                onClickBackIcon = {
                    onClickBackButton()
                },
            ) {
                IconButton(onClick = {
                    if (!state.challengeInfoUiModel.isFinished) { showSelectListDialog = true }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.height(9.dp))
            if (state.loadingIndicatorState) {
                Box(modifier = Modifier.fillMaxSize()) {
                    FlowerLoadingIndicator(
                        modifier = Modifier.width(128.dp).height(144.dp).align(Alignment.Center),
                    )
                }
            } else {
                ChallengeInfo(
                    state.challengeInfoUiModel,
                )
            }
            if (homeGoalAchievePartnerAndMeUiModel == null && state.homeGoalAchievePartnerAndMeUiModel != null) {
                TwoTooGoalAchievementProgressbar(
                    modifier = Modifier
                        .padding(top = 12.dp, start = 24.dp)
                        .width(210.dp)
                        .height(59.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(15.dp)),
                    homeGoalAchievePartnerAndMeUiModel = state.homeGoalAchievePartnerAndMeUiModel,
                )
            }
            OwnerNickNames(state.ownerNickNamesUiModel)
            Spacer(modifier = Modifier.height(12.dp))
            Divider(
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(horizontal = 24.dp),
            )
            Box {
                DottedLine()
                HistoryItems(
                    state.historyItemUiModel,
                    navigateToHistoryDetail,
                    ::showBottomSheet,
                )
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
                content = DialogContent.createHistoryLeaveChallengeDialogContent(
                    negativeAction = {
                        showChallengeDropDialog = false
                    },
                    positiveAction = {
                        quiteChallenge()
                        showChallengeDropDialog = false
                        onClickBackButton()
                    },
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
            onClickBackButton = {},
            state = HistoryState.default,
            navigateToHistoryDetail = {},
            onClickBottomSheetDataButton = {},
            quiteChallenge = {},
        )
    }
}

@Preview(name = "비어있을때")
@Composable
private fun PreviewHistoryScreenEmpty() {
    TwoTooTheme {
        HistoryScreen(
            onClickBackButton = {},
            state = HistoryState.default,
            navigateToHistoryDetail = {},
            onClickBottomSheetDataButton = {},
            quiteChallenge = {},
        )
    }
}

@Preview(name = "프로그래스바가 보이는 화면")
@Composable
private fun PreviewHistoryScreenWithProgressBar() {
    TwoTooTheme {
        HistoryScreen(
            homeGoalAchievePartnerAndMeUiModel = HomeGoalAchievePartnerAndMeUiModel.default,
            onClickBackButton = {},
            state = HistoryState.default,
            navigateToHistoryDetail = {},
            onClickBottomSheetDataButton = {},
            quiteChallenge = {},
        )
    }
}
