package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun ShareChallengeDialog(
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(),
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Box() {
            Column(
                Modifier.background(Color.Transparent, shape = TwoTooTheme.shape.medium),
            ) {
                Text(
                    text = "챌린지 완료 카드를 획득했어요!",
                    textAlign = TextAlign.Center,
                    style = TwoTooTheme.typography.headLineNormal20,
                    color = TwoTooTheme.color.mainWhite,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(12.dp)
                        .height(495.dp),
                ) {
                    ShareChallengeContent()
                    Spacer(modifier = Modifier.height(11.dp))
                    TwoTooTextButton(text = "공유하기", onClick = {}, modifier = Modifier.fillMaxWidth())
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "닫기",
                    textAlign = TextAlign.Center,
                    style = TwoTooTheme.typography.headLineNormal20,
                    color = TwoTooTheme.color.mainWhite,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}

@Composable
fun ShareChallengeContent() {
    Box(
        modifier = Modifier.background(
            color = TwoTooTheme.color.backgroundYellow,
            shape = TwoTooTheme.shape.extraSmall,
        ).height(450.dp),
    ) {
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 36.dp),
        ) {
            Text(
                text = "11월 9일 (목)",
                textAlign = TextAlign.Start,
                style = TwoTooTheme.typography.headLineNormal20,
                color = TwoTooTheme.color.mainBrown,
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = "하루 운동 30분 이상하기",
                textAlign = TextAlign.Start,
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
            Text(
                text = "1번째 챌린지 완료",
                textAlign = TextAlign.Start,
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.twoTooPink,
                modifier = Modifier.background(color = Color.White, shape = TwoTooTheme.shape.extraSmall).padding(10.dp),
            )
        }

        TwoTooImageView(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.33f),
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

        Row(modifier = Modifier.padding(top = 43.dp).align(Alignment.Center)) {
            TwoTooImageView(
                modifier = Modifier.size(99.dp, 164.dp),
                model = R.drawable.img_home_fifth_stage_camellia_me,
                previewPlaceholder = R.drawable.img_home_fifth_stage_camellia_me,
            )
            Spacer(modifier = Modifier.width(24.dp))
            TwoTooImageView(
                modifier = Modifier.size(99.dp, 164.dp),
                model = R.drawable.img_home_fifth_stage_sunflower_me,
                previewPlaceholder = R.drawable.img_home_fifth_stage_sunflower_me,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewShareChallengeDialog() {
    ShareChallengeDialog()
}
