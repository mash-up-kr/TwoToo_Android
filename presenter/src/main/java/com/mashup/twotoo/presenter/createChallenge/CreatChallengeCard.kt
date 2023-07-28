package com.mashup.twotoo.presenter.createChallenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.theme.MainYellow
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun CreateChallengeCard(
    challengeTitle: String,
    challengeDate: String,
    challengeDesc: String,
    isNextButtonVisible: Boolean = true,
    onClickNext: () -> Unit
) {
    var width by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(top = 46.dp)
                .background(Color.White, shape = TwoTooTheme.shape.extraSmall)
                .height(332.dp)
                .onGloballyPositioned {
                    width = it.size.width
                },
        ) {
            ChallengeCardInfoTop(challengeTitle, challengeDate, challengeDesc)
            ChallengeCardInfoBottom(
                modifier = Modifier
                    .width(with(LocalDensity.current) { width.toDp() })
                    .align(Alignment.BottomEnd),
            )
            TwoTooImageView(
                model = R.drawable.img_challenge_card_seed,
                modifier = Modifier.align(Alignment.BottomEnd)
                    .size(81.dp, 95.dp)
                    .padding(end = 8.dp, bottom = 21.dp),
                previewPlaceholder = R.drawable.img_challenge_card_seed,
                contentScale = ContentScale.FillBounds,
            )
        }
        if (isNextButtonVisible) {
            Spacer(Modifier.weight(1f))
            TwoTooTextButton(
                text = stringResource(id = R.string.button_next),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp),
                onClick = {
                    onClickNext()
                },
            )
            Spacer(modifier = Modifier.height(55.dp))
        }
    }
}

@Composable
fun ChallengeCardInfoTop(
    challengeTitle: String,
    challengeDate: String,
    challengeDesc: String,
) {
    Column(
        modifier = Modifier.wrapContentWidth().defaultMinSize(minWidth = 242.dp)
            .padding(horizontal = 23.dp),
    ) {
        Text(
            text = challengeTitle,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 46.dp, bottom = 6.dp),
            style = TwoTooTheme.typography.headLineNormal20,
            color = TwoTooTheme.color.mainBrown,
        )
        Text(
            text = challengeDate,
            textAlign = TextAlign.Start,
            style = TwoTooTheme.typography.headLineNormal20,
            color = TwoTooTheme.color.mainBrown,
        )
        Text(
            text = challengeDesc,
            textAlign = TextAlign.Start,
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.gray600,
            modifier = Modifier.padding(top = 18.dp),
        )
    }
}

@Composable
fun ChallengeCardInfoBottom(
    modifier: Modifier
) {
    Text(
        modifier = modifier.then(
            Modifier.background(
                color = MainYellow,
                shape = RoundedCornerShape(
                    0.dp,
                    0.dp,
                    10.dp,
                    10.dp,
                ),
            ).height(77.dp)
                .padding(top = 25.dp, end = 50.dp),
        ),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.twotoo_challenge),
        style = TwoTooTheme.typography.bodyNormal16,
        color = TwoTooTheme.color.mainBrown,
    )
}

@Preview
@Composable
private fun ChallengeCardPreview() {
    CreateChallengeCard(
        "하루 운동 30분 이상 하기",
        "2023/05/01 ~ 5/22",
        "운동사진으로 인증하기\n실패하는 사람은 뷔페 쏘기",
    ) {}
}
