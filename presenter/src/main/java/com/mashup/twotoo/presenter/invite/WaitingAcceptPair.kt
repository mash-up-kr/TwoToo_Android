package com.mashup.twotoo.presenter.invite

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooOutlineTextButton
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun WaitingAcceptPairRoute(
    inviteViewModel: InviteViewModel
) {
    WaitingAcceptPair(inviteViewModel)
}

@Composable
fun WaitingAcceptPair(
    inviteViewModel: InviteViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        TwoTooMainToolbar()
        Text(
            modifier = Modifier.padding(top = 120.dp, bottom = 28.dp),
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
            modifier = Modifier
                .size(185.dp, 128.dp)
                .padding(top = 32.dp),
            model = R.drawable.invite_waiting_heart,
            previewPlaceholder = R.drawable.img_waiting_mate_flower,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.weight(1f))
        WaitingInviteBottom(inviteViewModel)
    }
}

@Composable
fun WaitingInviteBottom(
    inviteViewModel: InviteViewModel
) {
    val state by inviteViewModel.collectAsState()
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    Column(
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
        ) {
            inviteViewModel.getPartnerInfo()
        }
        TwoTooOutlineTextButton(
            text = stringResource(id = R.string.resend_invite),
        ) {
            inviteViewModel.getUserInfo()
            if (state.userNo != 0) {
                inviteViewModel.createInviteCode(state.userNo, state.userNickName) { intent ->
                    launcher.launch(intent)
                }
            }
        }
    }
}

@Preview
@Composable
private fun WaitingAcceptPreview() {
    // WaitingAcceptPair()
}
