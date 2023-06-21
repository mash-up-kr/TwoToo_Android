package com.mashup.twotoo.presenter.createChallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun CreateChallengeRoute(
    onFinishChallengeInfo: () -> Unit
) {
    CreateChallenge(onFinishChallengeInfo)
}

@Composable
fun CreateChallenge(
    onFinishChallengeInfo: () -> Unit = {}
) {
    var currentStep by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TwoTooBackToolbar(onClickBackIcon = {
                if (currentStep > 1) currentStep--
            })
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .paint(
                    painterResource(id = R.drawable.image_background),
                    contentScale = ContentScale.FillBounds,
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.create_challenge_step, currentStep),
                    textAlign = TextAlign.Left,
                    style = TwoTooTheme.typography.headLineNormal28,
                    color = TwoTooTheme.color.mainBrown,
                )
                Text(
                    text = stringResource(id = R.string.create_challenge_desc_1),
                    style = TwoTooTheme.typography.bodyNormal14,
                    color = TwoTooTheme.color.gray600,
                    modifier = Modifier.padding(top = 12.dp),
                )

                when (currentStep) {
                    1 -> CreateChallengeOneStep()
                    2 -> CreateChallengeTwoStep()
                    3 -> CreateChallengeCard(
                        "하루 운동 30분 이상 하기",
                        "2023/05/01 ~ 5/22",
                        "운동사진으로 인증하기\n실패하는 사람은 뷔페 쏘기",
                    )
                }

                Spacer(Modifier.weight(1f))
                TwoTooTextButton(
                    text = stringResource(id = R.string.button_next),
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(57.dp),
                ) {
                    if (currentStep == 3) {
                        onFinishChallengeInfo()
                    } else {
                        currentStep++
                    }
                }
                Spacer(modifier = Modifier.height(55.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCreateChallengeOneStep() {
    CreateChallenge()
}
