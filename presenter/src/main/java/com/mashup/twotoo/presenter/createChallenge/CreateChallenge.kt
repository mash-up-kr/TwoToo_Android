package com.mashup.twotoo.presenter.createChallenge

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.createChallenge.model.ChallengeInfoModel
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.util.DateFormatter
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun CreateChallengeRoute(
    homeState: String = BeforeChallengeState.EMPTY.name,
    step: Int = 1,
    createChallengeViewModel: CreateChallengeViewModel,
    onBackToHome: () -> Unit,
    onFinishChallengeInfo: () -> Unit,
) {
    val state by createChallengeViewModel.collectAsState()
    var currentStep by remember { mutableIntStateOf(step) }

    CreateChallenge(
        homeState = homeState,
        state = state,
        currentStep = currentStep,
        updateOneStep = { challengeInfoModel ->
            createChallengeViewModel.setCreateChallengeInfo(challengeInfoModel, currentStep)
            currentStep++
        },
        updateTwoStep = { challengeInfoModel ->
            createChallengeViewModel.setCreateChallengeInfo(challengeInfoModel, currentStep)
            currentStep++
            Log.d(TAG, "CreateChallengeRoute: $state")
        },
        onClickTheeStep = {
            onFinishChallengeInfo()
        },
        onClickBackButton = {
            if (homeState == BeforeChallengeState.EMPTY.name || homeState == BeforeChallengeState.TERMINATION.name) {
                if (currentStep > 1) {
                    currentStep--
                } else {
                    onBackToHome()
                }
            } else {
                onBackToHome()
            }
        },
    )
}

@Composable
fun CreateChallenge(
    homeState: String = BeforeChallengeState.EMPTY.name,
    state: ChallengeInfoModel,
    currentStep: Int,
    updateOneStep: (ChallengeInfoModel) -> Unit,
    updateTwoStep: (ChallengeInfoModel) -> Unit,
    onClickTheeStep: () -> Unit,
    onClickBackButton: () -> Unit
) {
    val context = LocalContext.current
    val isNextButtonVisible = homeState == BeforeChallengeState.RESPONSE.name ||
        homeState == BeforeChallengeState.EMPTY.name ||
        homeState == BeforeChallengeState.TERMINATION.name

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        CreateChallengeToolbar(
            homeState = homeState,
            onClickBackButton = onClickBackButton,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 24.dp, end = 24.dp),
        ) {
            if (homeState == BeforeChallengeState.TERMINATION.name || homeState == BeforeChallengeState.EMPTY.name) {
                Text(
                    text = stringResource(id = R.string.create_challenge_step, currentStep),
                    textAlign = TextAlign.Left,
                    style = TwoTooTheme.typography.headLineNormal28,
                    color = TwoTooTheme.color.mainBrown,
                )
            }
            if (homeState == BeforeChallengeState.TERMINATION.name || homeState == BeforeChallengeState.EMPTY.name ||
                homeState == BeforeChallengeState.RESPONSE.name
            ) {
                Text(
                    text = stringResource(id = getCurrentStepString("title", currentStep, context), currentStep),
                    textAlign = TextAlign.Left,
                    style = TwoTooTheme.typography.headLineNormal28,
                    color = TwoTooTheme.color.mainBrown,
                )
                Text(
                    text = stringResource(id = getCurrentStepString("desc", currentStep, context)),
                    style = TwoTooTheme.typography.bodyNormal14,
                    color = TwoTooTheme.color.gray600,
                    modifier = Modifier.padding(top = 12.dp),
                )
            }
            when (currentStep) {
                1 -> CreateChallengeOneStep(state) { name, startDate, endDate ->
                    val period = DateFormatter.formatDateRange(startDate, endDate)
                    updateOneStep(
                        ChallengeInfoModel(
                            challengeName = name,
                            startDate = startDate,
                            endDate = endDate,
                            period = period,
                        ),
                    )
                }
                2 -> CreateChallengeTwoStep(state) { challengeInfo ->
                    updateTwoStep(ChallengeInfoModel(challengeInfo = challengeInfo))
                }
                3 -> CreateChallengeCard(
                    challengeTitle = state.challengeName,
                    challengeDesc = state.challengeInfo,
                    challengeDate = state.period,
                    isNextButtonVisible = isNextButtonVisible,
                    onClickNext = { onClickTheeStep() },
                )
            }
        }
    }
}

@Composable
fun CreateChallengeToolbar(homeState: String, onClickBackButton: () -> Unit) {
    val toolbarTitle = when (homeState) {
        BeforeChallengeState.WAIT.name, BeforeChallengeState.REQUEST.name -> stringResource(id = R.string.challenge_info)
        BeforeChallengeState.RESPONSE.name, BeforeChallengeState.EMPTY.name, BeforeChallengeState.TERMINATION.name -> ""
        else -> { "" }
    }
    TwoTooBackToolbar(
        title = toolbarTitle,
        onClickBackIcon = {
            onClickBackButton()
        },
    )
}

fun getCurrentStepString(name: String, currentStep: Int, context: Context): Int {
    val titleName = "create_challenge_${name}_$currentStep"
    return context.resources.getIdentifier(titleName, "string", context.packageName)
}

@Preview
@Composable
private fun PreviewCreateChallengeOneStep() {
    // CreateChallenge(CreateChallengeState(), {})
}
