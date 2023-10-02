package com.mashup.twotoo.presenter.designsystem.component.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun WaterLoadingIndicator(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -20f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )

    ConstraintLayout(
        modifier = modifier,
    ) {
        val (waterWrapper, waterImage) = createRefs()
        val screenHeight = LocalConfiguration.current.screenHeightDp
        val areaMargin = 12.dp * screenHeight / 812
        val bottomSpace = 14.dp * screenHeight / 812
        TwoTooImageView(
            modifier = Modifier.constrainAs(waterWrapper) {
                linkTo(
                    top = parent.top,
                    start = parent.start,
                    end = parent.end,
                    bottom = parent.bottom,
                )
            },
            model = R.drawable.ic_water_bubble_wrapper,
            previewPlaceholder = R.drawable.ic_water_bubble_wrapper,
            contentScale = ContentScale.Fit,
        )
        TwoTooImageView(
            modifier = Modifier.padding(
                top = areaMargin,
                bottom = areaMargin + bottomSpace,
                start = areaMargin,
                end = areaMargin,
            )
                .constrainAs(waterImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }.graphicsLayer {
                    rotationZ = rotation
                },
            model = R.drawable.img_watering,
            previewPlaceholder = R.drawable.img_watering,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun FlowerLoadingIndicator(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.flower_indicator))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}

@Preview
@Composable
fun PreviewWaterIndicator() {
    TwoTooTheme {
        WaterLoadingIndicator(
            modifier = Modifier.width(75.dp).height(69.dp),
        )
    }
}

@Preview
@Composable
fun PreviewFlowerIndicator() {
    TwoTooTheme {
        FlowerLoadingIndicator()
    }
}
