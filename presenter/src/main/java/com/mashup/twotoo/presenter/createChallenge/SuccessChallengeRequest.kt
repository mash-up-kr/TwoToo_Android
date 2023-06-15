package com.mashup.twotoo.presenter.createChallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun SuccessChallengeRequest() {
    Scaffold(
        topBar = { TwoTooBackToolbar(onClickBackIcon = {}) },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .paint(
                    painterResource(id = R.drawable.image_background),
                    contentScale = ContentScale.FillBounds,
                ),
        ) {
            SuccessChallengeCont(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 66.dp),
            )
            TwoTooImageView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(244.dp)
                    .align(Alignment.BottomCenter),
                previewPlaceholder = R.drawable.image_home_background,
                model = R.drawable.image_home_background,
            )
            TwoTooTextButton(
                text = stringResource(id = R.string.button_confirm),
                enabled = false,
                modifier = Modifier
                    .padding(bottom = 55.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .height(57.dp)
                    .align(Alignment.BottomCenter),
            ) {}
        }
    }
}

@Composable
private fun SuccessChallengeCont(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(id = R.string.send_challenge_request),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.headLineNormal28,
            color = TwoTooTheme.color.mainBrown,
        )
        Text(
            text = stringResource(id = R.string.success_challenge_request_desc),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.gray600,
        )
        TwoTooImageView(
            modifier = Modifier.padding(top = 65.dp).size(150.dp),
            model = R.drawable.invite_waiting_heart,
            previewPlaceholder = R.drawable.invite_waiting_heart,
        )
    }
}

@Preview
@Composable
private fun PreviewSuccessChallengeRequest() {
    SuccessChallengeRequest()
}
