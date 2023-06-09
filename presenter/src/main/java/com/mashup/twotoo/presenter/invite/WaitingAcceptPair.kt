package com.mashup.twotoo.presenter.invite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooOutlineTextButton
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun WaitingAcceptPair() {
    Scaffold(
        topBar = { TwoTooMainToolbar() },
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
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    modifier = Modifier.padding(top = 150.dp, bottom = 13.dp),
                    text = stringResource(id = R.string.invite_waiting_other),
                    textAlign = TextAlign.Center,
                    style = TwoTooTheme.typography.headLineNormal28,
                    color = TwoTooTheme.color.mainBrown,
                )
                Text(
                    text = stringResource(id = R.string.invite_refresh),
                    textAlign = TextAlign.Center,
                    style = TwoTooTheme.typography.bodyNormal16,
                    color = TwoTooTheme.color.gray600,
                )
                TwoTooImageView(
                    modifier = Modifier.size(150.dp).padding(top = 10.dp),
                    model = R.drawable.invite_waiting_heart,
                    previewPlaceholder = R.drawable.invite_waiting_heart,
                )
                Spacer(modifier = Modifier.weight(1f))
                WaitingInviteBottom()
            }
        }
    }
}

@Composable
fun WaitingInviteBottom() {
    Column(
        modifier = Modifier.padding(vertical = 55.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Text(
            text = stringResource(id = R.string.invite_resend_desc),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.gray600,
        )
        TwoTooTextButton(
            text = stringResource(id = R.string.refresh),
        ) {}
        TwoTooOutlineTextButton(
            text = stringResource(id = R.string.resend_invite),
        ) {}
    }
}

@Preview
@Composable
private fun WaitingAcceptPreview() {
    WaitingAcceptPair()
}
