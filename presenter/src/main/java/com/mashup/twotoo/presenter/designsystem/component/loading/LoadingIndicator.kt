package com.mashup.twotoo.presenter.designsystem.component.loading

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun WaterLoadingIndicator() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw._watering))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = Modifier.size(500.dp),
        composition = composition,
        progress = { progress },
    )
}

@Composable
fun FlowerLoadingIndicator() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.flower_indicator))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = Modifier.size(500.dp),
        composition = composition,
        progress = { progress },
    )
}

@Preview
@Composable
fun PreviewWaterIndicator() {
    TwoTooTheme {
        WaterLoadingIndicator()
    }
}

@Preview
@Composable
fun PreviewFlowerIndicator() {
    TwoTooTheme {
        FlowerLoadingIndicator()
    }
}
