package com.mashup.twotoo.presenter.invite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun SendInvitationRoute(
    sendInvitationButtonClick: () -> Unit = {}
) {
    SendInvitation(sendInvitationButtonClick)
}

@Composable
fun SendInvitation(
    sendInvitationButtonClick: () -> Unit = {}
) {
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
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = stringResource(id = R.string.invite_title),
                    modifier = Modifier.padding(top = 137.dp, bottom = 24.dp),
                    style = TwoTooTheme.typography.headLineNormal28,
                    color = TwoTooTheme.color.mainBrown,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(id = R.string.invite_desc),
                    style = TwoTooTheme.typography.bodyNormal16,
                    color = TwoTooTheme.color.gray600,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.weight(1f))
                TwoTooTextButton(
                    text = stringResource(id = R.string.send_invite),
                ) {
                    sendInvitationButtonClick()
                }
                Spacer(modifier = Modifier.height(55.dp))
            }
        }
    }
}

@Preview
@Composable
private fun SendInvitationPreview() {
    SendInvitation()
}
