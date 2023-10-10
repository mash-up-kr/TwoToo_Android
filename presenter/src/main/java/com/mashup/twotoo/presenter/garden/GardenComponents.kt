package com.mashup.twotoo.presenter.garden

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRound10
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import com.mashup.twotoo.presenter.garden.model.FlowerHead

@Composable
fun ChallengeCard(
    isStartAnimation: Boolean,
    challengeCardInfoUiModel: ChallengeCardInfoUiModel,
    navigateToGarden: (Int) -> Unit,
) {
    // 애니메이션 진행 중에만 border 적용
    val borderColor = if (isStartAnimation) TwoTooTheme.color.mainLightPink else Color.Transparent

    // 애니메이션을 위한 Offset 값
    val offsetY = remember {
        if (isStartAnimation) Animatable(-20f) else Animatable(0f)
    }
    LaunchedEffect(isStartAnimation) {
        if (isStartAnimation) {
            // 애니메이션이 진행 중인 경우 애니메이션 시작
            offsetY.animateTo(
                targetValue = 20f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 300, easing = FastOutLinearInEasing), RepeatMode.Reverse),
            )
        } else {
            // 애니메이션이 멈춘 경우 애니메이션 종료
            offsetY.stop()
        }
    }

    Box(
        modifier = Modifier
            .graphicsLayer(translationY = offsetY.value)
            .height(216.dp)
            .width(156.dp)
            .background(TwoTooTheme.color.mainWhite, TwoTooRound10)
            .clip(TwoTooRound10)
            .clickable {
                navigateToGarden(challengeCardInfoUiModel.challengeNo)
            },
    ) {
        ChallengeInfo(challengeCardInfoUiModel)
        TwoTooImageView(
            model = R.drawable.challenge_card_ground,
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            contentScale = ContentScale.Crop,
            previewPlaceholder = R.drawable.challenge_card_ground,
        )
        Flowers(challengeCardInfoUiModel)
    }
}

@Composable
private fun ChallengeInfo(challengeCardInfoUiModel: ChallengeCardInfoUiModel) {
    CompositionLocalProvider(
        LocalTextStyle provides TwoTooTheme.typography.bodyNormal14,
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(
                    id = if (challengeCardInfoUiModel.viewState.isInProgress()) {
                        R.string.inProgressChallengeAttempts
                    } else {
                        R.string.challengeAttempts
                    },
                    challengeCardInfoUiModel.attempts,
                ),
                color = if (challengeCardInfoUiModel.viewState.isInProgress()) {
                    TwoTooTheme.color.mainPink
                } else {
                    TwotooPink
                },
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = challengeCardInfoUiModel.name,
                color = TwoTooTheme.color.mainBrown,
            )
            Text(
                modifier = Modifier.padding(top = 7.dp),
                text = challengeCardInfoUiModel.period,
                color = TwoTooTheme.color.gray500,
            )
        }
    }
}

private fun String.isInProgress(): Boolean {
    return this == "InProgress"
}

@Composable
private fun BoxScope.Flowers(challengeCardInfoUiModel: ChallengeCardInfoUiModel) {
    Row(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 23.dp),
    ) {
        val context = LocalContext.current
        val screenWidth = LocalConfiguration.current.screenWidthDp
        val screenHeight = LocalConfiguration.current.screenHeightDp

        if (challengeCardInfoUiModel.viewState.isInProgress()) {
            val inProgressImageHeight = 64 * screenHeight / 812
            TwoTooImageView(
                modifier = Modifier.padding(horizontal = 29.dp).fillMaxWidth().height(inProgressImageHeight.dp),
                model = R.drawable.img_challenge_in_progress,
                previewPlaceholder = R.drawable.img_challenge_in_progress,
                contentScale = ContentScale.Fit,
            )
        } else {
            val meFlower = FlowerHead(challengeCardInfoUiModel.meFlower).getFlowerImage(context, screenWidth, screenHeight)
            val partnerFlower = FlowerHead(challengeCardInfoUiModel.partnerFlower).getFlowerImage(context, screenWidth, screenHeight)
            val isUser1Success = challengeCardInfoUiModel.user1CommitCnt > 15
            val isUser2Success = challengeCardInfoUiModel.user2CommitCnt > 15

            TwoTooImageView(
                modifier = Modifier.size(meFlower.width, meFlower.height),
                model = if (isUser1Success) meFlower.image else R.drawable.img_not_success,
                contentScale = ContentScale.Fit,
                previewPlaceholder = R.drawable.img_head_fig_sm,
            )
            TwoTooImageView(
                modifier = Modifier.size(partnerFlower.width, partnerFlower.height),
                model = if (isUser2Success) partnerFlower.image else R.drawable.img_not_success,
                contentScale = ContentScale.Fit,
                previewPlaceholder = R.drawable.img_head_camellia_sm,
            )
        }
    }
}

@Preview
@Composable
fun PreviewChallengeCardView() {
    ChallengeCard(false, ChallengeCardInfoUiModel.default[0], {})
}
