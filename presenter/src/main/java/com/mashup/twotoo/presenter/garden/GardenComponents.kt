package com.mashup.twotoo.presenter.garden

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRound6
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel

@Composable
fun ChallengeCard(challengeCardInfoUiModel: ChallengeCardInfoUiModel) {
    Box(
        modifier = Modifier.height(216.dp).width(156.dp).clip(TwoTooRound6).background(TwoTooTheme.color.mainWhite),
    ) {
        ChallengeInfo(challengeCardInfoUiModel)
        TwoTooImageView(
            model = R.drawable.challenge_card_ground,
            modifier = Modifier.height(58.dp).fillMaxWidth().align(Alignment.BottomStart),
            contentScale = ContentScale.Crop,
            previewPlaceholder = R.drawable.challenge_card_ground,
        )
        // Todo 꽃 선택하는 ui에서 사용하는 model 공통으로 사용하도록 해야함
        Flowers()
    }
}

@Composable
private fun ChallengeInfo(challengeCardInfoUiModel: ChallengeCardInfoUiModel) {
    CompositionLocalProvider(
        LocalTextStyle provides TwoTooTheme.typography.bodyNormal14,
    ) {
        Column(
            modifier = Modifier.padding(start = 14.dp, top = 19.dp).fillMaxWidth(),
        ) {
            Text(challengeCardInfoUiModel.attempts, color = TwoTooTheme.color.mainPink)
            Text(challengeCardInfoUiModel.name, color = TwoTooTheme.color.mainBrown)
            Text(challengeCardInfoUiModel.period, color = TwoTooTheme.color.gray500)
        }
    }
}

@Composable
private fun BoxScope.Flowers() {
    Row(
        modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 23.dp),
    ) {
        TwoTooImageView(
            modifier = Modifier.size(68.dp),
            model = R.drawable.flower_fig,
            contentScale = ContentScale.Fit,
            previewPlaceholder = R.drawable.flower_fig,
        )
        TwoTooImageView(
            modifier = Modifier.size(68.dp),
            model = R.drawable.flower_chrysanthemum,
            contentScale = ContentScale.Fit,
            previewPlaceholder = R.drawable.flower_chrysanthemum,
        )
    }
}

@Preview
@Composable
fun PreviewChallengeCardView() {
    ChallengeCard(ChallengeCardInfoUiModel.getChallengeCardInfoToPreview()[0])
}
