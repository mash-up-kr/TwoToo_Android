package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun FlowerLanguageDialog(
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(),
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Box(
            modifier = Modifier
                .background(
                    TwoTooTheme.color.backgroundYellow,
                    shape = TwoTooTheme.shape.medium,
                )
                .width(screenWidth - 102.dp),
        ) {
            TwoTooImageView(
                modifier = Modifier
                    .height(96.dp)
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .align(Alignment.BottomCenter),
                model = R.drawable.img_flower_card_bottom,
                previewPlaceholder = R.drawable.img_flower_card_bottom,

            )
            Column() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cancel),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 14.dp, end = 14.dp)
                        .clickable { },
                    contentDescription = null,
                )
                FlowerLanguageContent(
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(bottom = 32.dp),
                    R.string.fig,
                    R.string.fig_language,
                )
                TwoTooImageView(
                    modifier = Modifier.size(99.dp, 164.dp)
                        .align(Alignment.CenterHorizontally),
                    model = R.drawable.img_home_fourth_stage_fig_partner,
                    previewPlaceholder = R.drawable.img_home_fourth_stage_fig_partner,
                )
                Spacer(modifier = Modifier.height(22.dp))
                ChallengeCountCard(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 30.dp),
                    1,
                )
            }
        }
    }
}

@Composable
fun FlowerLanguageContent(modifier: Modifier, flower: Int, language: Int) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = flower),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.headLineNormal24,
            color = TwoTooTheme.color.mainBrown,
        )
        Text(
            text = stringResource(id = language),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.mainBrown,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun ChallengeCountCard(
    modifier: Modifier,
    challengeCount: Int
) {
    Text(
        modifier = modifier.then(
            Modifier
                .background(color = Color.White.copy(alpha = LocalContentAlpha.current), shape = RoundedCornerShape(4.dp))
                .padding(vertical = 4.dp, horizontal = 10.dp),
        ),
        text = stringResource(id = R.string.challenge_number, challengeCount),
        color = TwoTooTheme.color.twoTooPink,
        style = TwoTooTheme.typography.bodyNormal16,

    )
}

@Preview
@Composable
fun PreviewFlowerDialog() {
    FlowerLanguageDialog()
}
