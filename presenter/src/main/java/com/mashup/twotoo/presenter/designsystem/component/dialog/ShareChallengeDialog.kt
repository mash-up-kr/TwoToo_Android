package com.mashup.twotoo.presenter.designsystem.component.dialog

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeChallengeCompleteUiModel
import com.mashup.twotoo.presenter.util.DateFormatter

@Composable
fun ShareChallengeDialog(
    cardChallengeInfo: HomeChallengeCompleteUiModel = HomeChallengeCompleteUiModel(),
    onClickShareButton: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CompleteChallengeLottie(
                modifier = Modifier.fillMaxWidth().height(350.dp).align(Alignment.TopCenter),
            )
            Column(
                Modifier.padding(top = 30.dp)
                    .background(Color.Transparent)
                    .wrapContentHeight()
                    .align(Alignment.Center)
                    .padding(horizontal = 50.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.complete_challenge_get_card),
                    textAlign = TextAlign.Center,
                    style = TwoTooTheme.typography.headLineNormal20,
                    color = TwoTooTheme.color.mainWhite,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .background(Color.White, shape = TwoTooTheme.shape.medium)
                        .padding(12.dp)
                        .wrapContentHeight(),
                ) {
                    ShareChallengeContent(
                        context = context,
                        cardChallengeInfo = cardChallengeInfo,
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                    TwoTooTextButton(
                        text = stringResource(id = R.string.share),
                        onClick = { onClickShareButton() },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(id = R.string.close),
                    textAlign = TextAlign.Center,
                    style = TwoTooTheme.typography.headLineNormal20,
                    color = TwoTooTheme.color.mainWhite,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .clickable { onDismissRequest() },
                )
            }
        }
    }
}

@Composable
fun ShareChallengeContent(context: Context, cardChallengeInfo: HomeChallengeCompleteUiModel) {
    Column(
        modifier = Modifier.background(
            color = TwoTooTheme.color.backgroundYellow,
            shape = TwoTooTheme.shape.extraSmall,
        ).wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 36.dp),
        ) {
            Text(
                text = toCreatedDate(cardChallengeInfo.startDate),
                textAlign = TextAlign.Start,
                style = TwoTooTheme.typography.headLineNormal20,
                color = TwoTooTheme.color.mainBrown,
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = cardChallengeInfo.name,
                textAlign = TextAlign.Start,
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = stringResource(id = R.string.complete_challenge_attempts, cardChallengeInfo.count),
                textAlign = TextAlign.Start,
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.twoTooPink,
                modifier = Modifier.background(color = Color.White, shape = TwoTooTheme.shape.extraSmall).padding(10.dp),
            )
        }
        Box() {
            TwoTooImageView(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(100.dp),
                previewPlaceholder = R.drawable.image_home_background,
                model = R.drawable.image_home_background,
                contentScale = ContentScale.FillBounds,
            )
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Start,
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.mainWhite,
                modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 21.dp, start = 29.dp),
            )

            Row(modifier = Modifier.padding(bottom = 50.dp, top = 20.dp).align(Alignment.BottomCenter)) {
                TwoTooImageView(
                    modifier = Modifier.size(99.dp, 164.dp),
                    model = cardChallengeInfo.getFlowerImage(context, 1),
                    previewPlaceholder = cardChallengeInfo.getFlowerImage(context, 1),
                )
                Spacer(modifier = Modifier.width(24.dp))
                TwoTooImageView(
                    modifier = Modifier.size(99.dp, 164.dp),
                    model = cardChallengeInfo.getFlowerImage(context, 2),
                    previewPlaceholder = cardChallengeInfo.getFlowerImage(context, 2),
                )
            }
        }
    }
}

private fun toCreatedDate(createdDate: String): String {
    if (createdDate.isEmpty()) {
        return createdDate
    }
    val dateStr = DateFormatter.dateConvertToPlusNineTime(createdDate)
    val (month, day) = dateStr.split("-")
    return "${month}월 ${day}일"
}

@Composable
fun CompleteChallengeLottie(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.card_congratulation_lottie))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
    LottieAnimation(
        modifier = modifier,
        contentScale = ContentScale.FillWidth,
        composition = composition,
        progress = { progress },
    )
}

@Preview(backgroundColor = 0x000000, showBackground = true)
@Composable
private fun PreviewShareChallengeDialog() {
    ShareChallengeDialog()
}
