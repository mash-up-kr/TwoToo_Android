package com.mashup.twotoo.presenter.garden

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRound6
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun ChallengeCard(text: String) {
    Box(
        modifier = Modifier.height(216.dp).width(156.dp).clip(TwoTooRound6).background(TwoTooTheme.color.mainWhite),
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides TwoTooTheme.typography.bodyNormal14,
        ) {
            Column(
                modifier = Modifier.padding(start = 14.dp, top = 19.dp).fillMaxWidth(),
            ) {
                Text("6번째 챌린지", color = TwoTooTheme.color.mainPink)
                Text(text, color = TwoTooTheme.color.mainBrown)
                Text("2023/05/01 ~ 05/22", color = TwoTooTheme.color.gray500)
            }
        }
        Image(
            modifier = Modifier.height(58.dp).fillMaxWidth().align(Alignment.BottomStart),
            painter = painterResource(id = R.drawable.challenge_card_ground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 46.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.tulip),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
            Image(
                modifier = Modifier.padding(start = 15.dp),
                painter = painterResource(id = R.drawable.lose),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Preview
@Composable
fun PreviewChallengeCardView() {
    ChallengeCard("하루 운동 30분 이상 하기")
}
