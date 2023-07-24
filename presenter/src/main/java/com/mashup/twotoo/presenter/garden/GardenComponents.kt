package com.mashup.twotoo.presenter.garden

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRound6
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import com.mashup.twotoo.presenter.garden.model.FlowerHead
import com.mashup.twotoo.presenter.model.FlowerName
import kotlinx.coroutines.delay

@Composable
fun ChallengeCard(isStartAnimation: Boolean, challengeCardInfoUiModel: ChallengeCardInfoUiModel, navigateToGarden: (Int) -> Unit) {
    // 애니메이션 상태 변수 (3초 후에 애니메이션 종료)
    var startAnimation by remember { mutableStateOf(isStartAnimation) }
    LaunchedEffect(true) {
        delay(2000)
        startAnimation = false
    }

    // 애니메이션 진행 중에만 border 적용
    val borderColor = if (startAnimation) TwoTooTheme.color.mainLightPink else Color.Transparent

    // 애니메이션을 위한 Offset 값
    val offsetY = remember {
        if (startAnimation) Animatable(-20f) else Animatable(0f)
    }
    LaunchedEffect(startAnimation) {
        if (startAnimation) {
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
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .height(216.dp)
            .width(156.dp)
            .clip(TwoTooRound6)
            .background(TwoTooTheme.color.mainWhite)
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
        Flowers(challengeCardInfoUiModel.meFlower, challengeCardInfoUiModel.partnerFlower)
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
                    id = R.string.challengeAttempts,
                    challengeCardInfoUiModel.attempts,
                ),
                color = TwoTooTheme.color.mainPink,
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

@Composable
private fun BoxScope.Flowers(meFlower: FlowerName, partnerFlower: FlowerName) {
    Row(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 23.dp),
    ) {
        val context = LocalContext.current
        val meFlower = FlowerHead(meFlower).getFlowerImage(context)
        val partnerFlower = FlowerHead(partnerFlower).getFlowerImage(context)
        TwoTooImageView(
            modifier = Modifier.size(meFlower.width, meFlower.height),
            model = meFlower.image,
            contentScale = ContentScale.Fit,
            previewPlaceholder = R.drawable.img_head_fig_sm,
        )
        TwoTooImageView(
            modifier = Modifier.size(partnerFlower.width, partnerFlower.height),
            model = partnerFlower.image,
            contentScale = ContentScale.Fit,
            previewPlaceholder = R.drawable.img_head_camellia_sm,
        )
    }
}

@Preview
@Composable
fun PreviewChallengeCardView() {
    ChallengeCard(false, ChallengeCardInfoUiModel.default[0]) {}
}
