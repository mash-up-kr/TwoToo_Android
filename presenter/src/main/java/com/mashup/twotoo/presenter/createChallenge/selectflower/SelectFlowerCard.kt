package com.mashup.twotoo.presenter.createChallenge.selectflower

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.createChallenge.CreateChallengeSideEffect
import com.mashup.twotoo.presenter.createChallenge.CreateChallengeViewModel
import com.mashup.twotoo.presenter.createChallenge.model.ChallengeInfoModel
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SelectFlowerCardRoute(
    createChallengeViewModel: CreateChallengeViewModel,
    onClickBackButton: () -> Unit,
    onSuccessCreateChallenge: () -> Unit,
) {
    SelectFlowerCard(
        onStartButtonClick = { flowerName ->
            createChallengeViewModel.setChallengeInfo(ChallengeInfoModel(selectFlowerName = flowerName), 4)
            createChallengeViewModel.createChallenge()
        },
        onClickBackButton,
    )

    createChallengeViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CreateChallengeSideEffect.NavigateToSuccessCreate -> {
                Log.d(TAG, "SelectFlowerCardRoute: success")
                onSuccessCreateChallenge()
            }
            is CreateChallengeSideEffect.ToastMessage -> {}
            else -> {}
        }
    }
}

@Composable
fun SelectFlowerCard(
    onStartButtonClick: (String) -> Unit = {},
    onClickBackButton: () -> Unit = {}
) {
    var isVisibleStartButton by remember { mutableStateOf(false) }
    var flowerName by remember { mutableStateOf("") }
    Column {
        TwoTooBackToolbar(onClickBackIcon = {
            onClickBackButton()
        })
        Box {
            Column(modifier = Modifier.padding(top = 11.dp)) {
                SelectFlowerTitle()
                SelectFlowerLazyColumn(
                    onClickOneItem = { selectName ->
                        if (selectName.isNotEmpty()) {
                            isVisibleStartButton = true
                            flowerName = selectName
                        } else {
                            isVisibleStartButton = false
                        }
                    },
                )
            }
            if (isVisibleStartButton) {
                TwoTooTextButton(
                    text = stringResource(id = R.string.challenge_start),
                    enabled = true,
                    modifier = Modifier
                        .padding(bottom = 55.dp, start = 18.dp, end = 18.dp)
                        .fillMaxWidth()
                        .height(57.dp)
                        .align(Alignment.BottomCenter),
                ) {
                    onStartButtonClick(flowerName)
                }
            }
        }
    }
}

@Composable
fun SelectFlowerTitle() {
    Column(
        modifier = Modifier.padding(horizontal = 18.dp),
    ) {
        Text(
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = TwoTooTheme.typography.headLineNormal28.fontFamily,
                        fontSize = TwoTooTheme.typography.headLineNormal28.fontSize,
                        color = TwoTooTheme.color.mainBrown,
                    ),
                ) {
                    append(stringResource(id = R.string.during_challenge))
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = TwoTooTheme.typography.headLineNormal28.fontFamily,
                        fontSize = TwoTooTheme.typography.headLineNormal28.fontSize,
                        color = TwotooPink,
                    ),
                ) {
                    append(stringResource(id = R.string.pair))
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = TwoTooTheme.typography.headLineNormal28.fontFamily,
                        fontSize = TwoTooTheme.typography.headLineNormal28.fontSize,
                        color = TwoTooTheme.color.mainBrown,
                    ),
                ) {
                    append(stringResource(id = R.string.select_flower))
                }
            },
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
            text = stringResource(id = R.string.select_flower_desc),
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.gray600,
        )
    }
}

@Preview
@Composable
private fun PreviewSelectFlowerCard() {
    SelectFlowerCard()
}
