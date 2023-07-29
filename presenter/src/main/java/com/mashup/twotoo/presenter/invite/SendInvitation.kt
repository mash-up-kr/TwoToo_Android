package com.mashup.twotoo.presenter.invite

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SendInvitationRoute(
    inviteViewModel: InviteViewModel,
    sendInvitationOnSuccess: () -> Unit = {},
) {
    val state by inviteViewModel.collectAsState()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        inviteViewModel.navigateToWaitingAcceptPair()
    }

    SendInvitation(
        onTextButtonClick = {
            inviteViewModel.getUserInfo()
        },
    )

    inviteViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is InviteSideEffect.Toast -> {
            }
            is InviteSideEffect.NavigateToWaitingPair -> {
                sendInvitationOnSuccess()
            }
            is InviteSideEffect.NavigateToHome -> {}
            is InviteSideEffect.SendSharedInvitation -> {
                if (state.userNo != 0) {
                    inviteViewModel.storeIsSendInvitation(true)
                    inviteViewModel.createInviteCode(context, state.userNo, state.userNickName) { intent ->
                        launcher.launch(intent)
                    }
                }
            }
            is InviteSideEffect.MatchingPartner -> {}
        }
    }
}

@Composable
fun SendInvitation(
    onTextButtonClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        TwoTooMainToolbar()
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
        TwoTooImageView(
            modifier = Modifier
                .padding(top = 47.dp)
                .size(183.dp, 138.dp),
            previewPlaceholder = R.drawable.img_send_invitation_flower,
            model = R.drawable.img_send_invitation_flower,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.weight(1f))
        TwoTooTextButton(
            text = stringResource(id = R.string.send_invite),
            onClick = onTextButtonClick,
        )
        Spacer(modifier = Modifier.height(54.dp))
    }
}

@Preview
@Composable
private fun SendInvitationPreview() {
    // SendInvitation()
}
