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
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.createChallenge.model.ChallengeInfoModel
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.util.DateFormatter
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun CreateChallengeRoute(
    step: Int = 1,
    createChallengeViewModel: CreateChallengeViewModel,
    onBackToHome: () -> Unit,
    onFinishChallengeInfo: () -> Unit,
) {
    val state by createChallengeViewModel.collectAsState()
    var currentStep by remember { mutableIntStateOf(step) }

    CreateChallenge(
        state,
        currentStep,
        updateOneStep = { challengeInfoModel ->
            createChallengeViewModel.setChallengeInfo(challengeInfoModel, currentStep)
            currentStep++
        },
        updateTwoStep = { challengeInfoModel ->
            createChallengeViewModel.setChallengeInfo(challengeInfoModel, currentStep)
            currentStep++
            Log.d(TAG, "CreateChallengeRoute: $state")
        },
        onClickTheeStep = {
            onFinishChallengeInfo()
        },
        onClickBackButton = {
            if (currentStep > 1) {
                currentStep--
            } else {
                onBackToHome()
            }
        },
    )
}

@Composable
fun CreateChallenge(
    state: ChallengeInfoModel,
    currentStep: Int,
    updateOneStep: (ChallengeInfoModel) -> Unit,
    updateTwoStep: (ChallengeInfoModel) -> Unit,
    onClickTheeStep: () -> Unit,
    onClickBackButton: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TwoTooBackToolbar(onClickBackIcon = {
            onClickBackButton()
        })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 24.dp, end = 24.dp),
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
                    onClickNext = { onClickTheeStep() },
                )
            }
        }
    }
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
