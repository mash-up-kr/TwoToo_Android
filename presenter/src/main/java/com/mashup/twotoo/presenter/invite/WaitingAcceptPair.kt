package com.mashup.twotoo.presenter.invite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TwoTooTheme.color.backgroundYellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        TwoTooMainToolbar()
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = stringResource(id = R.string.invite_waiting_other),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.headLineNormal28,
            color = TwoTooTheme.color.mainBrown,
        )
        Spacer(modifier = Modifier.height(13.dp))
        Text(
            text = stringResource(id = R.string.invite_refresh),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.gray600,
        )
        Spacer(modifier = Modifier.height(10.dp))
        TwoTooImageView(
            modifier = Modifier.size(150.dp),
            model = R.drawable.invite_waiting_heart,
            previewPlaceholder = R.drawable.invite_waiting_heart,
        )
        Spacer(modifier = Modifier.height(50.dp))
        Spacer(modifier = Modifier.weight(1f))
        WaitingInviteBottom()
    }
}

@Composable
fun WaitingInviteBottom() {
    Text(
        text = stringResource(id = R.string.invite_resend_desc),
        textAlign = TextAlign.Center,
        style = TwoTooTheme.typography.bodyNormal14,
        color = TwoTooTheme.color.gray600,
    )
    Spacer(modifier = Modifier.height(18.dp))
    TwoTooTextButton(
        text = stringResource(id = R.string.refresh),
    ) {}
    Spacer(modifier = Modifier.height(18.dp))
    TwoTooOutlineTextButton(
        text = stringResource(id = R.string.resend_invite),
    ) {}
    Spacer(modifier = Modifier.height(55.dp))
}

@Preview
@Composable
private fun WaitingAcceptPreview() {
    WaitingAcceptPair()
}
